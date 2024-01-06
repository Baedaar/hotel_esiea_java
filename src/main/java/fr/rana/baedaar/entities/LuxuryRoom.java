package fr.rana.baedaar.entities;

public class LuxuryRoom extends Room {

    int numberOfBed;

    public LuxuryRoom(boolean isAvailable, int numberOfBed, float price) {
        super(isAvailable, "Luxury", price);
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
        return "LuxuryRoom{" +
                "numberOfBed=" + numberOfBed +
                ", id=" + id +
                ", isAvailable=" + isAvailable +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
