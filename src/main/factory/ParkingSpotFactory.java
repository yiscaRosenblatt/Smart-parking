package main.factory;

import main.model.ParkingSpot;
import main.model.RegularSpot;
import main.model.HandicappedSpot;

public class ParkingSpotFactory {

    // מחלקת Factory ליצירת מקומות חניה בהתאם לסוג

    /**
     * Factory Method:
     * מחזירה מופע חדש של מקום חניה מסוג 'רגיל' או 'נכים' לפי הקלט.
     * היתרון: ניתן להוסיף סוגים חדשים בעתיד מבלי לשנות את לוגיקת הקוד הראשית.
     */
    public static ParkingSpot createSpot(String type, String spotId) {
        switch (type.toLowerCase()) {
            case "regular":
                return new RegularSpot(spotId); // יוצר מקום חניה רגיל
            case "handicapped":
                return new HandicappedSpot(spotId); // יוצר מקום חניה לנכים
            default:
                throw new IllegalArgumentException("Unknown spot type: " + type); // מונע שגיאות
        }
    }

}
