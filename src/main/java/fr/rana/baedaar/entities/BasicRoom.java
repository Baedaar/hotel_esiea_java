package fr.rana.baedaar.entities;

public class BasicRoom extends Room {

    int numberOfBed;

    public BasicRoom(boolean isAvailable, int numberOfBed, float price) {
        super(isAvailable, "Basic", price);
        this.numberOfBed = numberOfBed;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }


    @Override
    public String toString() {
        return "BasicRoom{" +
                "numberOfBed=" + numberOfBed +
                ", id=" + id +
                ", isAvailable=" + isAvailable +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
