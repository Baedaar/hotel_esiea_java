package fr.rana.baedaar.service;

import fr.rana.baedaar.entities.Command;
import fr.rana.baedaar.entities.Dejeuner;
import fr.rana.baedaar.entities.Diner;
import fr.rana.baedaar.entities.PetitDejeuner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandService {

    private static final String COMMAND_FILE = "command.bin";

    public CommandService() {
        createFile();
    }

    public void saveCommand(Command command) {
        List<Command> commands = loadCommands();
        commands.add(command);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COMMAND_FILE))) {
            oos.writeObject(commands);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Command createCommand(float price, List<Diner> diner, List<Dejeuner> dejeuner, List<PetitDejeuner> petitDejeuner) {
        Command newCommand = new Command(price, diner, dejeuner, petitDejeuner);
        saveCommand(newCommand);
        return newCommand;
    }

    public List<Command> loadCommands() {
        List<Command> commands = new ArrayList<>();
        File file = new File(COMMAND_FILE);

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(COMMAND_FILE))) {
                commands = (List<Command>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Une erreur est survenue lors du chargement du fichier de commandes");
            }
        }
        return commands;
    }

    public float calculateTotalPrice(Command command) {
        float totalPrice = 0.0f;

        // Ajouter le prix des diners
        List<Diner> diners = command.getDiner();
        for (Diner diner : diners) {
            totalPrice += diner.getPrice();
        }

        // Ajouter le prix des dejeuners
        List<Dejeuner> dejeuners = command.getDejeuner();
        for (Dejeuner dejeuner : dejeuners) {
            totalPrice += dejeuner.getPrice();
        }

        // Ajouter le prix des petit-dejeuners
        List<PetitDejeuner> petitDejeuners = command.getPetitDejeuner();
        for (PetitDejeuner petitDejeuner : petitDejeuners) {
            totalPrice += petitDejeuner.getPrice();
        }

        command.setPrice(totalPrice);


        return totalPrice;
    }


    private void createFile() {
        try {
            File commandFile = new File(COMMAND_FILE);
            if (!commandFile.exists()) {
                commandFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Il y a une erreur lors de la création du fichier clients");
        }
    }
}
