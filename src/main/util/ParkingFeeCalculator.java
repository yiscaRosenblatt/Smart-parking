package main.util;

import main.model.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {
    public static double calculate(Vehicle vehicle) {
        if (vehicle.getEntryTime() == null || vehicle.getExitTime() == null) {
            return 0;
        }

        long minutes = Duration.between(vehicle.getEntryTime(), vehicle.getExitTime()).toMinutes();

        // לדוגמה: 5 שקלים לשעה, מחושב לפי דקות
        double ratePerMinute = 5.0 ;

        return minutes * ratePerMinute;
    }
}
