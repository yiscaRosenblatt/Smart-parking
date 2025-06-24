package main.model;

public class HandicappedSpot extends ParkingSpot {

    public HandicappedSpot(String spotId) {
        super(spotId);
    }

    @Override
    public String getType() {
        return "Handicapped";
    }
}
