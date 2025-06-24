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

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    // Getters
    public double getTotalRevenue() { return totalRevenue; }
    public int getTotalVehicles() { return totalVehicles; }
    public double getAverageParkingDuration() { return averageParkingDuration; }
    public int getTotalSpots() { return totalSpots; }
    public Map<LocalDate, Integer> getDailyParkingCount() { return dailyParkingCount; }
}
