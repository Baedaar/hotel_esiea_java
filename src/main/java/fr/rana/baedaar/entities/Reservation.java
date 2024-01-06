package fr.rana.baedaar.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Reservation {

    int id;
    Client client;
    float totalPrice;
    String details;
    long totalDays;
    LocalDate startDate;
    LocalDate endDate;
    Room room;
    List<Command> commands;


    public Reservation(int id, Client client, float totalPrice,
                       String details, int totalDays, LocalDate startDate,
                       LocalDate endDate, Room room, List<Command> commands) {
        this.id = id;
        this.client = client;
        this.totalPrice = totalPrice;
        this.details = details;
        this.totalDays = totalDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.commands = commands;
    }

    private long totalDays (LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}
