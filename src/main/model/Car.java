package main.model;

/**
 * מחלקה המייצגת רכב מסוג מכונית.
 * הרחבה של המחלקה Vehicle.
 *
 * דפוס עיצוב: Prototype
 * מאפשר לשכפל מופעים של רכבים (clone), על מנת לשמור היסטוריה מבלי לפגוע באובייקט המקורי.
 */
public class Car extends Vehicle {

    /**
     * בונה מכונית חדשה לפי מספר רישוי ושם בעלים.
     *
     * @param licensePlate מספר רישוי
     * @param ownerName    שם בעל הרכב
     */
    public Car(String licensePlate, String ownerName) {
        super(licensePlate, ownerName);
    }

    /**
     * משכפל את האובייקט Car הנוכחי.
     * שומר את זמני הכניסה/יציאה – חשוב לצורך דוחות.
     *
     * @return עותק חדש של Car עם אותם ערכים
     */
    @Override
    public Vehicle clone() {
        Car cloned = new Car(this.licensePlate, this.ownerName);
        cloned.setEntryTime(this.entryTime);
        cloned.setExitTime(this.exitTime);
        return cloned;
    }
}
