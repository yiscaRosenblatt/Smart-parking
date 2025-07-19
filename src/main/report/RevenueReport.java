package main.report;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import main.model.Vehicle;

/**
 * מחלקה זו אחראית ליצירת דוחות הכנסה עבור החניון.
 * כוללת מידע אודות הכנסות, מספר רכבים, שהות ממוצעת, וספירה יומית.
 * מיישמת את דפוס העיצוב Builder כאשר היא נתמכת ע״י מחלקת Builder חיצונית (לא מוצגת כאן).
 */
public class RevenueReport {
    private double totalRevenue;
    private int totalVehicles;
    private int totalSpots;
    private double averageParkingDuration;
    private Map<LocalDate, Integer> dailyParkingCount = new HashMap<>();
    private int currentVehicles;

    /**
     * מוסיף תשלום חדש לדוח ומעדכן את ספירת הרכבים הכוללת.
     * @param fee סכום התשלום שיש להוסיף.
     */
    public void addTransaction(double fee) {
        totalRevenue += fee;
        totalVehicles++;
    }

    /**
     * מחשבת את משך השהייה הממוצע של רכבים לפי רשימת הרכבים שנכנסו ויצאו.
     * @param vehicles רשימת רכבים.
     */
    public void calculateAverageDuration(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            averageParkingDuration = 0;
            return;
        }

        long totalMinutes = 0;
        for (Vehicle v : vehicles) {
            if (v.getEntryTime() != null && v.getExitTime() != null) {
                totalMinutes += Duration.between(v.getEntryTime(), v.getExitTime()).toMinutes();
            }
        }
        averageParkingDuration = totalMinutes / (double) vehicles.size();
    }

    /**
     * מעדכנת את מספר הרכבים שנכנסו בכל יום.
     * @param date התאריך של הכניסה.
     */
    public void updateDailyCount(LocalDate date) {
        dailyParkingCount.put(date, dailyParkingCount.getOrDefault(date, 0) + 1);
    }

    /**
     * מדפיסה את דוח ההכנסות למסך.
     * כולל סה״כ רכבים, הכנסה, זמן ממוצע, וספירה יומית.
     */
    public void printReport() {
        System.out.println("\n=== Parking Revenue Report ===");
        System.out.println("Revenue Report");
        System.out.println("Total spots: 5");
        System.out.println("Total vehicles parked: " + getCurrentVehicles());
        System.out.println("Total revenue: $" + totalRevenue);
        System.out.println("Average parking duration: " + averageParkingDuration + " minutes");
        System.out.println("Daily parking count:");
        for (Map.Entry<LocalDate, Integer> entry : dailyParkingCount.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue() + " vehicles");
        }
        System.out.println("==============================\n");
    }

    /**
     * מעדכן את מספר הרכבים הנוכחי בכניסה חדשה.
     */
    public void vehicleEntered() {
        currentVehicles++;
    }

    /**
     * מעדכן את מספר הרכבים הנוכחי כאשר רכב יוצא.
     */
    public void vehicleExited() {
        if (currentVehicles > 0) currentVehicles--;
    }

    /**
     * מחזיר את מספר הרכבים הנוכחי בחניון.
     * @return מספר רכבים.
     */
    public int getCurrentVehicles() {
        return currentVehicles;
    }

    /**
     * הגדרה מרוכזת של נתוני הדוח, משמשת לדפוס Builder.
     */
    public void setFromBuilder(double totalRevenue, int totalVehicles, int totalSpots,
                               double avgDuration, Map<LocalDate, Integer> countMap, int currentVehicles) {
        this.totalRevenue = totalRevenue;
        this.totalVehicles = totalVehicles;
        this.totalSpots = totalSpots;
        this.averageParkingDuration = avgDuration;
        this.dailyParkingCount = countMap;
        this.currentVehicles = currentVehicles;
    }

    // === Getters ===

    /**
     * @return סך ההכנסות מהחניון.
     */
    public double getTotalRevenue() { return totalRevenue; }

    /**
     * @return סך הרכבים שעברו בחניון.
     */
    public int getTotalVehicles() { return totalVehicles; }

    /**
     * @return משך השהות הממוצע ברכב.
     */
    public double getAverageParkingDuration() { return averageParkingDuration; }

    /**
     * @return מספר מקומות החניה הכולל.
     */
    public int getTotalSpots() { return totalSpots; }

    /**
     * @return מפת ספירה של כמות הרכבים לפי תאריך.
     */
    public Map<LocalDate, Integer> getDailyParkingCount() { return dailyParkingCount; }
}
