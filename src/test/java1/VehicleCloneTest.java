package test.java1;

import main.model.Car;

/**
 * {@code VehicleCloneTest} היא מחלקת בדיקה פשוטה שמוודאת
 * את תקינות השכפול (clone) של רכבי {@link Car} במערכת.
 * <p>
 * הבדיקה כוללת:
 * <ul>
 *     <li>בדיקה שהשכפול מחזיר אובייקט חדש (כלומר, מצביע שונה בזיכרון)</li>
 *     <li>בדיקה שהתכנים (מספר רישוי) נשמרים כהלכה בשכפול</li>
 * </ul>
 */
public class VehicleCloneTest {

    /**
     * נקודת הכניסה לבדיקה.
     * יוצרת רכב מקורי ומשכפלת אותו, ואז מדפיסה את הערכים
     * כדי לוודא תקינות השכפול.
     *
     * @param args לא בשימוש
     */
    public static void main(String[] args) {
        Car original = new Car("1234567", "yisca");
        Car copy = (Car) original.clone();

        System.out.println("Original plate: " + original.getLicensePlate());
        System.out.println("Copy plate: " + copy.getLicensePlate());

        // בדיקה אם זה אובייקט אחר בזיכרון
        System.out.println("Same object? " + (original == copy)); // אמור להיות false
        System.out.println("Same plate? " + original.getLicensePlate().equals(copy.getLicensePlate())); // אמור להיות true
    }
}
