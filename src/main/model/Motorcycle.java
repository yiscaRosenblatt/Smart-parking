package main.model;

/**
 * מייצגת אופנוע שנכנס לחניון, תת-מחלקה של {@link Vehicle}.
 * כוללת תכונה נוספת – האם יש מקום לאחסון קסדה.
 */
public class Motorcycle extends Vehicle {
    private boolean hasHelmetStorage;

    /**
     * בונה אופנוע חדש עם מספר רישוי, שם בעלים, וציון האם יש מקום לקסדה.
     *
     * @param licensePlate מספר הרישוי של האופנוע
     * @param ownerName שם הבעלים
     * @param hasHelmetStorage האם יש מקום אחסון לקסדה
     */
    public Motorcycle(String licensePlate, String ownerName, boolean hasHelmetStorage) {
        super(licensePlate, ownerName);
        this.hasHelmetStorage = hasHelmetStorage;
    }

    /**
     * מחזיר האם לאופנוע יש מקום לקסדה.
     *
     * @return true אם יש מקום לקסדה, אחרת false.
     */
    public boolean hasHelmetStorage() {
        return hasHelmetStorage;
    }

    /**
     * מגדיר האם יש מקום לקסדה.
     *
     * @param hasHelmetStorage true אם יש מקום, אחרת false.
     */
    public void setHasHelmetStorage(boolean hasHelmetStorage) {
        this.hasHelmetStorage = hasHelmetStorage;
    }

    /**
     * משכפל את האופנוע הנוכחי – כולל מידע על בעלים, רישוי, זמנים, ואחסון קסדה.
     *
     * @return מופע חדש של Motorcycle עם אותם נתונים.
     */
    @Override
    public Vehicle clone() {
        Motorcycle cloned = new Motorcycle(this.licensePlate, this.ownerName, this.hasHelmetStorage);
        cloned.setEntryTime(this.entryTime);
        cloned.setExitTime(this.exitTime);
        return cloned;
    }
}
