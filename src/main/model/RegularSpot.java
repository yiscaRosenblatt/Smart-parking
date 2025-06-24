package main.model;

public class RegularSpot extends ParkingSpot {

    public RegularSpot(String id) {
        super(id);
    }

    @Override
    public String getType() {
        return "Regular";
    }
}
