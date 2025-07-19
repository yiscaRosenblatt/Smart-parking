package main.model;

import java.time.LocalDateTime;

/**
 * מחלקה אבסטרקטית המייצגת רכב בחניון.
 * כוללת פרטים כמו מספר רישוי, שם הבעלים, זמן כניסה וזמן יציאה.
 * מיושם בה דפוס העיצוב Prototype לשכפול רכבים.
 */
public abstract class Vehicle implements Cloneable {
    protected String licensePlate;
    protected String ownerName;
    protected LocalDateTime entryTime;
    protected LocalDateTime exitTime;

    /**
     * בונה רכב חדש עם מספר רישוי ושם בעלים.
     * @param licensePlate מספר הרישוי של הרכב
     * @param ownerName שם הבעלים
     */
    public Vehicle(String licensePlate, String ownerName) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
    }

    /**
     * קובע את זמן הכניסה של הרכב.
     * @param time זמן הכניסה
     */
    public void setEntryTime(LocalDateTime time) {
        this.entryTime = time;
    }

    /**
     * קובע את זמן היציאה של הרכב.
     * @param time זמן היציאה
     */
    public void setExitTime(LocalDateTime time) {
        this.exitTime = time;
    }

    /**
     * מחזיר את זמן היציאה של הרכב.
     * @return זמן היציאה
     */
    public LocalDateTime getExitTime() {
        return exitTime;
    }

    /**
     * מחזיר את זמן הכניסה של הרכב.
     * @return זמן הכניסה
     */
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    /**
     * מחזיר את מספר הרישוי של הרכב.
     * @return מספר רישוי
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * מחזיר את שם הבעלים של הרכב.
     * @return שם בעלים
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * מעדכן את מספר הרישוי של הרכב.
     * @param licensePlate מספר רישוי חדש
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * מעדכן את שם הבעלים של הרכב.
     * @param ownerName שם בעלים חדש
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * פונקציית שכפול עבור דפוס Prototype.
     * מחזירה עותק חדש של הרכב עם אותם נתונים.
     * @return עותק חדש של הרכב
     */
    public abstract Vehicle clone();


}
