package fr.rana.baedaar.entities;

public class BasicRoom extends Room {

    int numberOfBed;
    float price;

    public BasicRoom(boolean isAvailable, int numberOfBed, float price) {
        super(isAvailable, "Basic");
        this.numberOfBed = numberOfBed;
        this.price = price;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BasicRoom{" +
                "numberOfBed=" + numberOfBed +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", type='" + type + '\'' +
                '}';
    }
}
