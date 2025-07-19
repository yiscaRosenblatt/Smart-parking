package main.model;

/**
 * מחלקה אבסטרקטית שמייצגת מקום חניה בחניון.
 * כל מקום חניה כולל מזהה, סטטוס תפוס/פנוי, ורכב נוכחי אם יש.
 * משמשת בסיס למחלקות כמו RegularSpot ו-HandicappedSpot.
 */
public abstract class ParkingSpot {
    protected String spotId;
    protected boolean isOccupied;
    protected Vehicle currentVehicle;

    /**
     * בונה מקום חניה חדש עם מזהה.
     * @param spotId מזהה ייחודי למקום החניה
     */
    public ParkingSpot(String spotId) {
        this.spotId = spotId;
        this.isOccupied = false;
        this.currentVehicle = null;
    }

    /**
     * @return מזהה מקום החניה.
     */
    public String getSpotId() {
        return spotId;
    }

    /**
     * @return האם מקום החניה תפוס כרגע.
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * @return הרכב החונה כרגע במקום זה (null אם פנוי).
     */
    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    /**
     * משייך רכב למקום החניה.
     * @param vehicle הרכב שייכנס למקום החניה
     */
    public void assignVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.isOccupied = true;
    }

    /**
     * משחרר את מקום החניה ומסיר את הרכב ממנו.
     */
    public void releaseVehicle() {
        this.currentVehicle = null;
        this.isOccupied = false;
    }

    /**
     * @return מחרוזת המתארת את סוג המקום ("Regular" / "Handicapped").
     */
    public abstract String getType();
}
