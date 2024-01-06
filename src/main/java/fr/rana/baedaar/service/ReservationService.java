package fr.rana.baedaar.service;

import fr.rana.baedaar.entities.*;
import fr.rana.baedaar.exceptions.DocumentCreationException;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReservationService {

    private static final String RESERVATION_FILE = "reservation.bin";
    private Random random = new Random();


    public ReservationService() {
        createFile();
    }

    public Reservation createReservation(Client client, LocalDate startDate,
                                         LocalDate endDate, Room room,
                                         boolean includeBreakfast, boolean includeLunch, boolean includeDinner) {
        List<Command> commands = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            float dailyPrice = 0.0f;
            List<Diner> diners = includeDinner ? Arrays.asList(new Diner()) : new ArrayList<>();
            List<Dejeuner> dejeuners = includeLunch ? Arrays.asList(new Dejeuner()) : new ArrayList<>();
            List<PetitDejeuner> petitDejeuners = includeBreakfast ? Arrays.asList(new PetitDejeuner()) : new ArrayList<>();

            if (includeDinner) dailyPrice += diners.get(0).getPrice();
            if (includeLunch) dailyPrice += dejeuners.get(0).getPrice();
            if (includeBreakfast) dailyPrice += petitDejeuners.get(0).getPrice();

            if (dailyPrice > 0) {
                commands.add(new Command(dailyPrice, diners, dejeuners, petitDejeuners));
            }
        }

        int totalDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
        float totalPrice = calculateTotalPrice(room, commands, totalDays);
        int reservationNumber = random.nextInt();

        Reservation newReservation = new Reservation(reservationNumber, client, totalPrice,
                "Details", totalDays, startDate, endDate, room, commands);
        saveReservation(newReservation);
        return newReservation;
    }


    private float calculateTotalPrice(Room room, List<Command> commands, int totalDays) {

        float totalPrice = room.getPrice() * totalDays;
        for (Command command : commands) {
            totalPrice += command.getPrice();
        }
        System.out.println(totalPrice);
        return totalPrice;
    }

    private void saveReservation(Reservation reservation) {
        List<Reservation> reservations = loadReservations();
        reservations.add(reservation);
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(RESERVATION_FILE))) {
            stream.writeObject(reservations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveReservations(List<Reservation> reservations) throws DocumentCreationException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RESERVATION_FILE))) {
            outputStream.writeObject(reservations);
        } catch (IOException e) {
            throw new DocumentCreationException("Il y a eu un probleme lors de la création du document", e);
        }
    }


    public List<Reservation> loadReservations() {
        List<Reservation> reservations = new ArrayList<>();
        File file = new File(RESERVATION_FILE);

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RESERVATION_FILE))) {
                reservations = (List<Reservation>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Une erreur est survenue lors du chargement du fichier de réservations");
            }
        }
        return reservations;
    }

    public boolean updateReservation(int reservationId, Client client, LocalDate startDate,
                                     LocalDate endDate, Room room, List<Command> commands) throws DocumentCreationException {
        List<Reservation> reservations = loadReservations();
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationNumber() == reservationId) {
                int totalDays = (int) ChronoUnit.DAYS.between(startDate, endDate);
                float totalPrice = calculateTotalPrice(room, commands, totalDays);
                reservations.set(i, new Reservation(reservationId, client, totalPrice,
                        "Details", totalDays, startDate, endDate, room, commands));
                saveReservations(reservations);
                return true;
            }
        }
        return false;
    }

    public boolean deleteReservation(int reservationId) throws DocumentCreationException {
        List<Reservation> reservations = loadReservations();
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationNumber() == reservationId) {
                reservations.remove(i);
                saveReservations(reservations);
                return true;
            }
        }
        return false;
    }

    public List<Reservation> findReservationsByClient(Client client) {
        List<Reservation> reservations = loadReservations();
        List<Reservation> clientReservations = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getClient().getId() == client.getId()) {
                clientReservations.add(reservation);
            }
        }

        System.out.println("Trouvé " + clientReservations.size() + " réservations pour le client " + client);

        return clientReservations;
    }


    private void createFile() {
        try {
            File commandFile = new File(RESERVATION_FILE);
            if (!commandFile.exists()) {
                commandFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Il y a une erreur lors de la création du fichier clients");
        }
    }

}
