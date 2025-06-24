package main.model;

import main.factory.ParkingSpotFactory;
import main.observer.ParkingObserver;
import main.report.RevenueReport;
import main.util.ParkingFeeCalculator;

import java.time.LocalDate;
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
    private RevenueReport revenueReport;




    public ParkingLot() {
        this.spots = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.entranceGate = new Gate();
        this.exitGate = new Gate();
        this.spotFactory = new ParkingSpotFactory();// Factory
        this.revenueReport = new RevenueReport();

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
                clone.setEntryTime(LocalDateTime.now());
                entranceGate.open();
                spot.assignVehicle(clone);
                vehicles.add(clone);
                revenueReport.vehicleEntered();
                revenueReport.updateDailyCount(LocalDate.now()); // ⬅️ חדש
                notifyObservers("Vehicle entered: " + clone.getLicensePlate());
                System.out.println("Vehicle " + clone.getLicensePlate() + " parked at spot " + spot.getSpotId());
                entranceGate.close();
                return;
            }
        }
        System.out.println("No available spots for vehicle: " + vehicle.getLicensePlate());
    }


    public void exitVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            Vehicle v = spot.getCurrentVehicle();
            if (v != null && v.getLicensePlate().equals(licensePlate)) {
                v.setExitTime(LocalDateTime.now());
                double fee = ParkingFeeCalculator.calculate(v);
                revenueReport.addTransaction(fee);
                revenueReport.calculateAverageDuration(vehicles);
                exitGate.open();
                spot.releaseVehicle();
                vehicles.removeIf(vehicle -> vehicle.getLicensePlate().equals(licensePlate));
                revenueReport.vehicleExited();
                notifyObservers("Vehicle exited: " + licensePlate);
                exitGate.close();
                return;
            }
        }
        System.out.println("Vehicle not found: " + licensePlate);
    }

    public RevenueReport getRevenueReport() {
        return revenueReport;
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
