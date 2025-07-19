package main.model;

import main.factory.ParkingSpotFactory;
import main.observer.ParkingObserver;
import main.report.RevenueReport;
import main.util.ParkingFeeCalculator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * מייצגת את החניון – ניהול מקומות חניה, רכבים, שערים, תשלום ודוחות.
 * משמשת כמרכז הפעולה של המערכת.
 */
public class ParkingLot {
    private List<ParkingSpot> spots;
    private List<Vehicle> vehicles;
    private List<ParkingObserver> observers;
    private Gate entranceGate;
    private Gate exitGate;
    private ParkingSpotFactory spotFactory;
    private RevenueReport revenueReport;
    private final double pricePerMinute = 5.0;
    private List<Vehicle> exitedVehicles = new ArrayList<>();

    /**
     * בונה חניון חדש ומאתחל את כל הרכיבים הפנימיים (שערים, רשימות וכו').
     */
    public ParkingLot() {
        this.spots = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.entranceGate = new Gate();
        this.exitGate = new Gate();
        this.spotFactory = new ParkingSpotFactory(); // Factory
        this.revenueReport = new RevenueReport();
    }

    /**
     * מוסיף מקום חניה חדש לפי סוג ומזהה.
     * @param type סוג המקום ("regular" או "handicapped")
     * @param spotId מזהה ייחודי למקום
     */
    public void addSpot(String type, String spotId) {
        ParkingSpot spot = spotFactory.createSpot(type, spotId);
        spots.add(spot);
    }

    /**
     * מכניס רכב לחניון אם יש מקום פנוי.
     * מבצע שכפול של הרכב (Prototype), פותח שער, מעדכן דוח, ושומר את הרכב.
     * @param vehicle הרכב הנכנס
     */
    public void enterVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                Vehicle clone = vehicle.clone(); // שימוש ב־Prototype
                clone.setEntryTime(LocalDateTime.now());
                entranceGate.open();
                spot.assignVehicle(clone);
                vehicles.add(clone);
                revenueReport.vehicleEntered();
                revenueReport.updateDailyCount(LocalDate.now());
                notifyObservers("Vehicle entered: " + clone.getLicensePlate());
                System.out.println("Vehicle " + clone.getLicensePlate() + " parked at spot " + spot.getSpotId());
                entranceGate.close();
                return;
            }
        }
        System.out.println("No available spots for vehicle: " + vehicle.getLicensePlate());
    }

    /**
     * מטפל ביציאת רכב מהחניון: מחשב תשלום, סוגר שער, מעדכן דוח, מסיר מהרשימה.
     * @param licensePlate מספר רישוי של הרכב היוצא
     */
    public void exitVehicle(String licensePlate) {
        for (ParkingSpot spot : spots) {
            Vehicle v = spot.getCurrentVehicle();
            if (v != null && v.getLicensePlate().equals(licensePlate)) {
                v.setExitTime(LocalDateTime.now());
                exitedVehicles.add(v);
                double fee = ParkingFeeCalculator.calculate(v, pricePerMinute);
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

    /**
     * @return מחזיר את דוח ההכנסות של החניון.
     */
    public RevenueReport getRevenueReport() {
        return revenueReport;
    }

    /**
     * @return רשימת מקומות חניה פנויים.
     */
    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> available = new ArrayList<>();
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                available.add(spot);
            }
        }
        return available;
    }

    /**
     * מוסיף מאזין (Observer) לאירועים בחניון.
     * @param o מאזין חדש
     */
    public void addObserver(ParkingObserver o) {
        observers.add(o);
    }

    /**
     * מודיע לכל המאזינים על אירוע מסוים (כניסה/יציאה).
     * @param event טקסט שמתאר את האירוע
     */
    private void notifyObservers(String event) {
        for (ParkingObserver o : observers) {
            o.update(event);
        }
    }

    /**
     * @return רשימת כל מקומות החניה בחניון.
     */
    public List<ParkingSpot> getSpots() {
        return spots;
    }

    /**
     * @return רשימת רכבים שנמצאים כרגע בחניון.
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * @return מחיר לדקת חניה.
     */
    public double getPricePerMinute() {
        return pricePerMinute;
    }

    /**
     * @return רשימת רכבים שכבר יצאו מהחניון (לצורך היסטוריה).
     */
    public List<Vehicle> getExitedVehicles() {
        return exitedVehicles;
    }
}
