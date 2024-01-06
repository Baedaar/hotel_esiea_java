package fr.rana.baedaar.entities;


import java.io.Serializable;

public class Diner implements Serializable {


    float price;
    String hoursOfDisponibility;

    public Diner() {
        this.price = 30;
        hoursOfDisponibility = "18h-22h";
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getHoursOfDisponibility() {
        return hoursOfDisponibility;
    }

    public void setHoursOfDisponibility(String hoursOfDisponibility) {
        this.hoursOfDisponibility = hoursOfDisponibility;
    }
}
