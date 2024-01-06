package fr.rana.baedaar;

import fr.rana.baedaar.entities.*;
import fr.rana.baedaar.service.ClientService;
import fr.rana.baedaar.service.CommandService;
import fr.rana.baedaar.service.RoomService;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        // Test de l'inscription d'un client
        Client newClient = clientService.inscription("John", "Doe", 123456789, 101, "password123");
        System.out.println("Nouveau client inscrit : " + newClient.toString());

        // Test de la connexion d'un client
        Client loggedInClient = clientService.connexion("Doe", "password123");
        if (loggedInClient != null) {
            System.out.println("Connexion réussie pour : " + loggedInClient);
        } else {
            System.out.println("Échec de la connexion");
        }

        // Charger tous les clients pour vérifier
        System.out.println("Chargement de tous les clients...");
        List<Client> clients = clientService.loadClients();
        for (Client client : clients) {
            System.out.println(client.toString());
        }

        // Instance de RoomService
        RoomService roomService = new RoomService();

        // Test de création et sauvegarde d'une chambre de luxe
        LuxuryRoom luxuryRoom = roomService.createLuxuryRoom(true, 2, 300.0f);
        System.out.println("Nouvelle chambre de luxe enregistrée : " + luxuryRoom);

        // Test de création et sauvegarde d'une chambre basique
        BasicRoom basicRoom = roomService.createBasicRoom(true, 1, 150.0f);
        System.out.println("Nouvelle chambre basique enregistrée : " + basicRoom);

        // Charger et afficher toutes les chambres
        System.out.println("Chargement de toutes les chambres...");
        List<Room> allRooms = roomService.loadRooms();
        for (Room room : allRooms) {
            System.out.println(room);
        }

        CommandService commandService = new CommandService();

        // Créer des repas pour les commandes
        Diner diner = new Diner(20.0f);
        Dejeuner dejeuner = new Dejeuner(15.0f);
        PetitDejeuner petitDejeuner = new PetitDejeuner(10.0f);

        // Test de création et sauvegarde d'une commande
        Command newCommand = commandService.createCommand(45.0f,
                Arrays.asList(diner), Arrays.asList(dejeuner), Arrays.asList(petitDejeuner));
        System.out.println("Nouvelle commande enregistrée : " + newCommand);

        // Charger et afficher toutes les commandes
        System.out.println("Chargement de toutes les commandes...");
        List<Command> allCommands = commandService.loadCommands();
        for (Command command : allCommands) {
            System.out.println(command);
        }
    }
}