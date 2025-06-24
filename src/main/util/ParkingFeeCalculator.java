package main.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingFeeCalculator {
    public static double calculateFee(LocalDateTime entry, LocalDateTime exit) {
        long minutes = Duration.between(entry, exit).toMinutes();
        return minutes * 0.5; // חצי שקל לדקה
    }
}
