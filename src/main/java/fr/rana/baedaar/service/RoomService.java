package fr.rana.baedaar.service;

import fr.rana.baedaar.entities.BasicRoom;
import fr.rana.baedaar.entities.Client;
import fr.rana.baedaar.entities.LuxuryRoom;
import fr.rana.baedaar.entities.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private static final String ROOM_FILE = "room.bin";

    public RoomService() {
        createFile();
    }

    private void saveRoom(Room newRoom) {
        List<Room> rooms = loadRooms(); // Charger les chambres existantes
        rooms.add(newRoom); // Ajouter la nouvelle chambre

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROOM_FILE))) {
            oos.writeObject(rooms); // Sauvegarder la liste entière
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LuxuryRoom createLuxuryRoom(boolean isAvailable, int numberOfBed, float price) {
        LuxuryRoom luxuryRoom = new LuxuryRoom(isAvailable, numberOfBed, price);
        saveRoom(luxuryRoom);
        return luxuryRoom;
    }

    public BasicRoom createBasicRoom(boolean isAvailable, int numberOfBed, float price) {
        BasicRoom basicRoom = new BasicRoom(isAvailable, numberOfBed, price);
        saveRoom(basicRoom);
        return basicRoom;
    }


    public List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();
        File file = new File(ROOM_FILE);

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ROOM_FILE))) {
                Object obj = inputStream.readObject();
                if (obj instanceof List) {
                    rooms = (List<Room>) obj;
                } else if (obj instanceof Room) {
                    rooms.add((Room) obj);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Une erreur est survenue lors du chargement du fichier des chambres");
            }
        }
        return rooms;
    }


    public List<LuxuryRoom> getAllAvailableLuxuryRooms() {
        List<LuxuryRoom> luxuryRooms = new ArrayList<>();
        List<Room> allRoom = loadRooms();

        for (int i = 0; i < allRoom.size() ; i++) {
            if (allRoom.get(i).getType().equals("Luxury") && allRoom.get(i).isAvailable()) {
                luxuryRooms.add((LuxuryRoom) allRoom.get(i));
            }
        }
        return luxuryRooms;
    }

    public List<BasicRoom> getAllAvailableBasicRooms() {
        List<BasicRoom> basicRooms = new ArrayList<>();
        List<Room> allRoom = loadRooms();

        for (int i = 0; i < allRoom.size() ; i++) {
            if (allRoom.get(i).getType().equals("Basic") && allRoom.get(i).isAvailable()) {
                basicRooms.add((BasicRoom) allRoom.get(i));
            }
        }
        return basicRooms;
    }


    private void createFile() {
        try {
            File roomfile = new File(ROOM_FILE);
            if (!roomfile.exists()) {
                roomfile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Il y a une erreur lors de la création du fichier clients");
        }
    }
}
