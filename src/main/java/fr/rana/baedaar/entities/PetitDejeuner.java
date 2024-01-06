package fr.rana.baedaar.entities;


import java.io.Serializable;

public class PetitDejeuner implements Serializable {

    float price;
    String hoursOfDisponibility;

    public PetitDejeuner(float price) {
        this.price = price;
        hoursOfDisponibility = "6h-11h";
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
