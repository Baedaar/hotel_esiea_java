package fr.rana.baedaar.entities;

import java.io.Serializable;
import java.util.Random;

public class Room implements Serializable {

    int id;
    boolean isAvailable;
    String type;
    float price;
    Random random = new Random();

    public Room( boolean isAvailable, String type, float price) {
        this.id = random.nextInt(10000);
        this.isAvailable = isAvailable;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
