package fr.rana.baedaar.entities;

import java.io.Serializable;
import java.util.Random;

public class Room implements Serializable {

    int id;
    boolean isAvailable;
    String type;
    Random random = new Random();

    public Room( boolean isAvailable, String type) {
        this.id = random.nextInt(10000);
        this.isAvailable = isAvailable;
        this.type = type;
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
}
