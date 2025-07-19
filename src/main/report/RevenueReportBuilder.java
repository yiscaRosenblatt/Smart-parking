package main.report;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * {@code RevenueReportBuilder} היא מחלקה המיישמת את דפוס העיצוב Builder
 * ומאפשרת בנייה גמישה ומדורגת של אובייקט {@link RevenueReport}.
 */
public class RevenueReportBuilder {
    private double totalRevenue;
    private int totalVehicles;
    private int totalSpots;
    private double averageParkingDuration;
    private Map<LocalDate, Integer> dailyParkingCount;
    private int currentVehicles;

    /**
     * מגדיר את סך ההכנסות לדוח.
     * @param totalRevenue סך ההכנסות
     * @return האובייקט הנוכחי לצורך שירשור (chaining)
     */
    public RevenueReportBuilder setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
        return this;
    }

    /**
     * מגדיר את מספר הרכבים הכולל.
     * @param totalVehicles מספר הרכבים
     * @return האובייקט הנוכחי לצורך שירשור
     */
    public RevenueReportBuilder setTotalVehicles(int totalVehicles) {
        this.totalVehicles = totalVehicles;
        return this;
    }

    /**
     * מגדיר את מספר מקומות החניה הכולל.
     * @param totalSpots מספר המקומות
     * @return האובייקט הנוכחי לצורך שירשור
     */
    public RevenueReportBuilder setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
        return this;
    }

    /**
     * מגדיר את משך השהות הממוצע.
     * @param avg משך השהות בדקות
     * @return האובייקט הנוכחי לצורך שירשור
     */
    public RevenueReportBuilder setAverageParkingDuration(double avg) {
        this.averageParkingDuration = avg;
        return this;
    }

    /**
     * מגדיר את מפת ספירת הרכבים היומית.
     * @param map מפת <תאריך, כמות רכבים>
     * @return האובייקט הנוכחי לצורך שירשור
     */
    public RevenueReportBuilder setDailyParkingCount(Map<LocalDate, Integer> map) {
        this.dailyParkingCount = map;
        return this;
    }

    /**
     * מגדיר את מספר הרכבים הנוכחיים בחניון.
     * @param count מספר רכבים נוכחיים
     * @return האובייקט הנוכחי לצורך שירשור
     */
    public RevenueReportBuilder setCurrentVehicles(int count) {
        this.currentVehicles = count;
        return this;
    }

    /**
     * בונה את האובייקט {@link RevenueReport} עם הנתונים שהוגדרו.
     * @return מופע חדש של {@link RevenueReport}
     */
    public RevenueReport build() {
        RevenueReport report = new RevenueReport();
        report.setFromBuilder(totalRevenue, totalVehicles, totalSpots, averageParkingDuration, dailyParkingCount, currentVehicles);
        return report;
    }
}
