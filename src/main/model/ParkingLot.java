package main.model;

import main.factory.ParkingSpotFactory;
import main.observer.ParkingObserver;
import main.util.ParkingFeeCalculator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> spots;
    private List<Vehicle> vehicles;
    private List<ParkingObserver> observers;
    private Gate entranceGate;
    private Gate exitGate;
    private ParkingSpotFactory spotFactory;


    public ParkingLot() {
        this.spots = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.entranceGate = new Gate();
        this.exitGate = new Gate();
        this.spotFactory = new ParkingSpotFactory();  // Factory
    }

    // מאפשר להוסיף מקום חדש
    public void addSpot(String type, String spotId) {
        ParkingSpot spot = spotFactory.createSpot(type, spotId);
        spots.add(spot);
    }

    public void enterVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                Vehicle clone = vehicle.clone(); // שימוש ב־Prototype
                clone.setEntryTime(LocalDateTime.now()); // ⬅️ הוספת זמן כניסה כאן
                entranceGate.open(); // נפתח שער רק כשיש מקום
                spot.assignVehicle(clone);
                vehicles.add(clone);
                notifyObservers("Vehicle entered: " + clone.getLicensePlate());
                System.out.println("Vehicle " + clone.getLicensePlate() + " parked at spot " + spot.getSpotId());
                entranceGate.close();
                return;
            }
        }
        System.out.println("No available spots for vehicle: " + vehicle.getLicensePlate());
        // לא נפתח שער במקרה שאין מקום
    }



    public void exitVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            Vehicle v = spot.getCurrentVehicle();
            if (v != null && v.getLicensePlate().equals(licensePlate)) {

                double fee = ParkingFeeCalculator.calculateFee(v.getEntryTime(), LocalDateTime.now());
                System.out.println("Total fee: " + fee + "₪");

                spot.releaseVehicle();
                vehicles.removeIf(vehicle -> vehicle.getLicensePlate().equals(licensePlate));
                notifyObservers("Vehicle exited: " + licensePlate);
                exitGate.close(); // אם יש exitGate
                return;
            }
        }
        System.out.println("Vehicle not found: " + licensePlate);
    }



    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> available = new ArrayList<>();
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                available.add(spot);
            }
        }
        return available;
    }


    public void addObserver(ParkingObserver o) {
        observers.add(o);
    }

    private void notifyObservers(String event) {
        for (ParkingObserver o : observers) {
            o.update(event);
        }
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
