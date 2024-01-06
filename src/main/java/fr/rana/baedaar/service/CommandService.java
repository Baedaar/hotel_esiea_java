package fr.rana.baedaar.service;


import java.io.*;


public class CommandService {

    private static final String COMMAND_FILE = "command.bin";

    public CommandService() {
        createFile();
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
