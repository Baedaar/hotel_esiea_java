package fr.rana.baedaar;

import fr.rana.baedaar.entities.*;
import fr.rana.baedaar.exceptions.DocumentCreationException;
import fr.rana.baedaar.service.ClientService;
import fr.rana.baedaar.service.CommandService;
import fr.rana.baedaar.service.ReservationService;
import fr.rana.baedaar.service.RoomService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DocumentCreationException {
        Scanner scanner = new Scanner(System.in);
        ClientService clientService = new ClientService();
        RoomService roomService = new RoomService();
        CommandService commandService = new CommandService();
        ReservationService reservationService = new ReservationService();

        LuxuryRoom luxuryRoom = roomService.createLuxuryRoom(true, 2, 300.0f);
        LuxuryRoom luxuryRoom1 = roomService.createLuxuryRoom(true, 1, 200.0f);
        BasicRoom basicRoom = roomService.createBasicRoom(true, 1, 150.0f);
        BasicRoom basicRoom1 = roomService.createBasicRoom(true, 2, 100.0f);


        System.out.println("Avez-vous un compte utilisateur? (oui/non)");
        String hasAccount = scanner.nextLine();

        Client connectedClient;
        if (hasAccount.equalsIgnoreCase("non")) {
            System.out.println("Entrez votre prénom:");
            String firstName = scanner.nextLine();
            System.out.println("Entrez votre nom:");
            String lastName = scanner.nextLine();
            System.out.println("Entrez votre numéro de téléphone:");
            int phoneNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Choisissez un mot de passe:");
            String password = scanner.nextLine();
            connectedClient = clientService.inscription(firstName, lastName, phoneNumber, 101, password);
            System.out.println("Nouveau client inscrit : " + connectedClient.toString());
        } else {
            System.out.println("Entrez votre nom:");
            String lastName = scanner.nextLine();
            System.out.println("Entrez votre mot de passe:");
            String password = scanner.nextLine();
            connectedClient = clientService.connexion(lastName, password);
            if (connectedClient == null) {
                System.out.println("Échec de la connexion.");
                return;
            }
        }

        List<Reservation> clientReservations = reservationService.findReservationsByClient(connectedClient);

        if (connectedClient != null) {
            for (int i = 0; i < clientReservations.size(); ++i) {
                System.out.println(clientReservations.get(i).toString());
            }
            System.out.println("Souhaitez-vous [1] Faire une nouvelle réservation," +
                    " [2] Modifier une réservation existante," +
                    " ou [3] Supprimer une réservation ?");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    List<Room> availableRooms = roomService.loadRooms();
                    for (Room room : availableRooms) {
                        if (room.isAvailable()) {
                            System.out.println(room);
                        }
                    }

                    System.out.println("Entrez l'ID de la chambre que vous souhaitez réserver:");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    List<Room> allRoom = roomService.loadRooms();
                    Room selectedRoom = null;
                    for (Room room : allRoom) {
                        if (room.getId() == roomId && room.isAvailable()) {
                            selectedRoom = room;
                            selectedRoom.setAvailable(false);
                            roomService.updateRoom(selectedRoom.getId());
                            break;
                        }
                    }
                    if (selectedRoom == null) {
                        System.out.println("Aucune chambre trouvée avec l'ID spécifié.");
                        return;
                    }

                    System.out.println("Entrez la date de début de réservation (yyyy-mm-dd):");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Entrez la date de fin de réservation (yyyy-mm-dd):");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    List<Command> commands = new ArrayList<>();
                    System.out.println("Voulez-vous inclure le petit déjeuner chaque jour ? (oui/non)");
                    boolean includeBreakfast = scanner.nextLine().equalsIgnoreCase("oui");

                    System.out.println("Voulez-vous inclure le déjeuner chaque jour ? (oui/non)");
                    boolean includeLunch = scanner.nextLine().equalsIgnoreCase("oui");

                    System.out.println("Voulez-vous inclure le dîner chaque jour ? (oui/non)");
                    boolean includeDinner = scanner.nextLine().equalsIgnoreCase("oui");

                    Reservation newReservation = reservationService.createReservation(connectedClient,
                            startDate, endDate, selectedRoom, includeBreakfast, includeLunch,
                            includeDinner);
                    System.out.println("Réservation effectuée : " + newReservation);

                    break;

                case 2:
                    System.out.println("Entrez l'ID de la réservation à modifier:");
                    int reservationId = scanner.nextInt();
                    scanner.nextLine();

                    Reservation reservationAModifier = null;
                    List<Reservation> allReservations = reservationService.loadReservations();
                    for (Reservation r : allReservations) {
                        if (r.getReservationNumber() == reservationId) {
                            reservationAModifier = r;
                            break;
                        }
                    }

                    if (reservationAModifier == null) {
                        System.out.println("Aucune réservation trouvée avec l'ID spécifié.");
                        break;
                    }

                    System.out.println("Entrez la nouvelle date de début (yyyy-mm-dd):");
                    LocalDate newStartDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Entrez la nouvelle date de fin (yyyy-mm-dd):");
                    LocalDate newEndDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Voulez-vous changer la chambre? (oui/non)");
                    String changeRoom = scanner.nextLine();

                    Room roomToReserve = reservationAModifier.getRoom();
                    if (changeRoom.equalsIgnoreCase("oui")) {
                        reservationAModifier.getRoom().setAvailable(true);
                        List<Room> rooms = roomService.loadRooms();
                        for (Room room : rooms) {
                            System.out.println(room);
                        }

                        System.out.println("Entrez l'ID de la nouvelle " +
                                "chambre que vous souhaitez réserver:");
                        int newRoomId = scanner.nextInt();
                        scanner.nextLine();

                        for (Room r : rooms) {
                            if (r.getId() == newRoomId) {
                                roomToReserve = r;
                                break;
                            }
                        }

                        if (roomToReserve == null) {
                            System.out.println("Aucune chambre trouvée avec l'ID spécifié.");
                            break;
                        }
                    }

                    List<Command> commandList = new ArrayList<>();;
                    System.out.println("Voulez-vous inclure le petit déjeuner chaque jour ? (oui/non)");
                    boolean includeBreakfast1 = scanner.nextLine().equalsIgnoreCase("oui");

                    System.out.println("Voulez-vous inclure le déjeuner chaque jour ? (oui/non)");
                    boolean includeLunch1 = scanner.nextLine().equalsIgnoreCase("oui");

                    System.out.println("Voulez-vous inclure le dîner chaque jour ? (oui/non)");
                    boolean includeDinner1 = scanner.nextLine().equalsIgnoreCase("oui");

                    boolean isUpdated = reservationService.updateReservation(reservationId, connectedClient, newStartDate, newEndDate, roomToReserve, commandList);
                    if (isUpdated) {
                        System.out.println("Réservation mise à jour avec succès.");
                    } else {
                        System.out.println("Échec de la mise à jour de la réservation.");
                    }
                    break;

                case 3:
                    System.out.println("Entrez l'ID de la réservation à supprimer:");
                    int reservationIdToDelete = scanner.nextInt();
                    scanner.nextLine();

                    boolean isDeleted = reservationService.deleteReservation(reservationIdToDelete);
                    if (isDeleted) {
                        System.out.println("Réservation supprimée avec succès.");
                    } else {
                        System.out.println("Échec de la suppression de la réservation.");
                    }
                    break;

                default:
                    System.out.println("Choix non valide.");
                    break;
            }
        } else {
            System.out.println("Identification échouée.");
        }
        scanner.close();
    }
}