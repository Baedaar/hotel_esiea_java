package fr.rana.baedaar.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Command implements Serializable {

    int id;
    float price;
    List<Diner> diner;
    List<Dejeuner> dejeuner;
    List<PetitDejeuner> petitDejeuner;
    Random random = new Random();

    public Command(float price, List<Diner> diner, List<Dejeuner> dejeuner, List<PetitDejeuner> petitDejeuner) {
        this.id = random.nextInt(1000);
        this.price = price;
        this.diner = diner;
        this.dejeuner = dejeuner;
        this.petitDejeuner = petitDejeuner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Diner> getDiner() {
        return diner;
    }

    public void setDiner(List<Diner> diner) {
        this.diner = diner;
    }

    public List<Dejeuner> getDejeuner() {
        return dejeuner;
    }

    public void setDejeuner(List<Dejeuner> dejeuner) {
        this.dejeuner = dejeuner;
    }

    public List<PetitDejeuner> getPetitDejeuner() {
        return petitDejeuner;
    }

    public void setPetitDejeuner(List<PetitDejeuner> petitDejeuner) {
        this.petitDejeuner = petitDejeuner;
    }

    @Override
    public String toString() {
        return "Command{" +
                "price=" + price +
                ", diner=" + diner +
                ", dejeuner=" + dejeuner +
                ", petitDejeuner=" + petitDejeuner +
                '}';
    }
}
