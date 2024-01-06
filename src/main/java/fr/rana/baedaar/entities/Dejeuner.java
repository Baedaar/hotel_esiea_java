package fr.rana.baedaar.entities;


import java.io.Serializable;

public class Dejeuner implements Serializable {

    float price;
    String hoursOfDisponibility;

    public Dejeuner(float price) {
        this.price = price;
        hoursOfDisponibility = "11h-14H";
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
