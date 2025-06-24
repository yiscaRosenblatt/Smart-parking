package main.factory;

import main.model.ParkingSpot;
import main.model.RegularSpot;
import main.model.HandicappedSpot;

public class ParkingSpotFactory {

    public static ParkingSpot createSpot(String type, String spotId) {
        switch (type.toLowerCase()) {
            case "regular":
                return new RegularSpot(spotId);
            case "handicapped":
                return new HandicappedSpot(spotId);
            default:
                throw new IllegalArgumentException("Unknown spot type: " + type);
        }
    }
}
