package main.model;

/**
 * מייצגת מקום חניה רגיל בחניון.
 * יורשת מהמחלקה האבסטרקטית {@link ParkingSpot}.
 */
public class RegularSpot extends ParkingSpot {

    /**
     * בונה מקום חניה רגיל עם מזהה ייחודי.
     * @param id מזהה המקום
     */
    public RegularSpot(String id) {
        super(id);
    }

    /**
     * מחזירה את סוג המקום - "Regular".
     * @return מחרוזת "Regular"
     */
    @Override
    public String getType() {
        return "Regular";
    }
}
