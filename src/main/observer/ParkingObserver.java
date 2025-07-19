package main.observer;

/**
 * ממשק המייצג צופה (Observer) אשר מקבל עדכונים על אירועים בחניון.
 * מהווה חלק מיישום של דפוס העיצוב Observer.
 * כל מחלקה שמיישמת את הממשק יכולה להגיב לשינויים כמו כניסה/יציאה של רכבים.
 */
public interface ParkingObserver {

    /**
     * פעולה המתבצעת כאשר מתקבל אירוע מהחניון.
     * @param event מחרוזת המתארת את האירוע (למשל "Vehicle entered: 1234")
     */
    void update(String event);
}
