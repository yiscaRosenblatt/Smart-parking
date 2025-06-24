package main.report;


import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import main.model.Vehicle;


public class RevenueReport {
    private double totalRevenue;
    private int totalVehicles;
    private int totalSpots;
    private double averageParkingDuration;
    private Map<LocalDate, Integer> dailyParkingCount = new HashMap<>();
    private int currentVehicles;





//    public RevenueReport(String title, double totalRevenue) {
//        this.title = title;
//        this.totalRevenue = totalRevenue;
//    }

    public void addTransaction(double fee) {
        totalRevenue += fee;
        totalVehicles++;
    }




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

    public void updateDailyCount(LocalDate date) {
        dailyParkingCount.put(date, dailyParkingCount.getOrDefault(date, 0) + 1);
    }


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


    public void vehicleEntered() {
        currentVehicles++;
    }

    public void vehicleExited() {
        if (currentVehicles > 0) currentVehicles--;
    }

    public int getCurrentVehicles() {
        return currentVehicles;
    }
    // Getters
    public double getTotalRevenue() { return totalRevenue; }
    public int getTotalVehicles() { return totalVehicles; }
    public double getAverageParkingDuration() { return averageParkingDuration; }
    public int getTotalSpots() { return totalSpots; }
    public Map<LocalDate, Integer> getDailyParkingCount() { return dailyParkingCount; }
}
