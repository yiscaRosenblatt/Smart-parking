package main.model;

/**
 * מייצגת מקום חניה לנכים.
 * תת-מחלקה של {@link ParkingSpot}, מיועדת לרכבים בעלי תו נכה.
 */
public class HandicappedSpot extends ParkingSpot {

    /**
     * בונה מקום חניה לנכים עם מזהה ייחודי.
     *
     * @param spotId מזהה המקום
     */
    public HandicappedSpot(String spotId) {
        super(spotId);
    }

    /**
     * מחזירה את סוג מקום החניה.
     *
     * @return מחרוזת "Handicapped"
     */
    @Override
    public String getType() {
        return "Handicapped";
    }
}
