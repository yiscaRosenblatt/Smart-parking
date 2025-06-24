package main.model;

import main.model.Vehicle;

public abstract class ParkingSpot {
    protected String spotId;
    protected boolean isOccupied;
    protected Vehicle currentVehicle;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
        this.isOccupied = false;
        this.currentVehicle = null;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.isOccupied = true;
    }

    public void releaseVehicle() {
        this.currentVehicle = null;
        this.isOccupied = false;
    }




    public abstract String getType(); // "Regular" / "Handicapped"
}
