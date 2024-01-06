package fr.rana.baedaar.service;

import fr.rana.baedaar.entities.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static final String CLIENT_FILE = "clients.bin";

    public ClientService() {
        createFile();
    }

    public Client inscription(String firstName, String lastName,
                              int phoneNumber, int roomNumber,
                              String password) {
        Client client = new Client(firstName,  lastName,  phoneNumber,  roomNumber,  password);
        saveClient(client);
        return client;
    }

    private void saveClient(Client newClient) {
        List<Client> clients = loadClients(); // Charger les clients existants
        clients.add(newClient); // Ajouter le nouveau client

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CLIENT_FILE))) {
            oos.writeObject(clients); // Sauvegarder la liste entière
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client connexion(String lastName, String password) {
        List<Client> clients = loadClients();
        for (Client client : clients) {
            if (client.getLastName().equals(lastName) && client.getPassword().equals(password)) {
                return client;
            }
        }
        return null;
    }

    public List<Client> loadClients() {
        File file = new File(CLIENT_FILE);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CLIENT_FILE))) {
                Object obj = inputStream.readObject();
                if (obj instanceof List) {
                    return (List<Client>) obj;
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Une erreur est survenue lors du chargement du fichier client");
            }
        }
        return new ArrayList<>();
    }


    private void createFile() {
        try {
            File clientfile = new File(CLIENT_FILE);
            if (!clientfile.exists()) {
                clientfile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Il y a une erreur lors de la création du fichier clients");
        }
    }


}
