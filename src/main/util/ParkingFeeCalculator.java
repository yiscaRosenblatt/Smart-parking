package main.util;

import main.model.Vehicle;
import java.time.Duration;

/**
 * {@code ParkingFeeCalculator} היא מחלקת שירות (utility)
 * לחישוב עלות השהות של רכב בחניון לפי זמן החניה ותעריף לדקה.
 */
public class ParkingFeeCalculator {

    /**
     * מחשבת את דמי החניה של רכב מסוים.
     *
     * @param vehicle הרכב שעבורו מחושבת העלות
     * @param ratePerMinute תעריף לדקת חניה
     * @return הסכום לתשלום, או 0 אם חסרים זמנים
     */
    public static double calculate(Vehicle vehicle, double ratePerMinute) {
        if (vehicle.getEntryTime() == null || vehicle.getExitTime() == null) {
            return 0;
        }

        long minutes = Duration.between(vehicle.getEntryTime(), vehicle.getExitTime()).toMinutes();
        return minutes * ratePerMinute;
    }
}
