package main.util;

import main.model.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {
    public static double calculate(Vehicle vehicle, double ratePerMinute) {
        if (vehicle.getEntryTime() == null || vehicle.getExitTime() == null) {
            return 0;
        }
        long minutes = Duration.between(vehicle.getEntryTime(), vehicle.getExitTime()).toMinutes();
        return minutes * ratePerMinute;
    }

}
