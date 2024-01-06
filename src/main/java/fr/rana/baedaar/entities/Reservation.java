package fr.rana.baedaar.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Reservation implements Serializable {

    int reservationNumber;
    Client client;
    float totalPrice;
    String details;
    long totalDays;
    LocalDate startDate;
    LocalDate endDate;
    Room room;
    List<Command> commands;


    public Reservation(int reservationNumber, Client client, float totalPrice,
                       String details, int totalDays, LocalDate startDate,
                       LocalDate endDate, Room room, List<Command> commands) {
        this.reservationNumber = reservationNumber;
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

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + reservationNumber +
                ", client=" + client +
                ", totalPrice=" + totalPrice +
                ", details='" + details + '\'' +
                ", totalDays=" + totalDays +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", room=" + room +
                ", commands=" + commands +
                '}';
    }
}
