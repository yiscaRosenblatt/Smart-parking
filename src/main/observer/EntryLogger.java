package main.observer;

/**
 * מממש את ממשק {@link ParkingObserver} לצורך תיעוד אירועים.
 * משמש כלוגר פשוט שמדפיס את האירועים המתקבלים למסך.
 * דוגמה ליישום של דפוס העיצוב Observer.
 */
public class EntryLogger implements ParkingObserver {

    /**
     * מתבצע בעת שינוי (כניסה או יציאה) ומדפיס את האירוע ללוג.
     * @param event תיאור האירוע (למשל "Vehicle entered: 1234")
     */
    @Override
    public void update(String event) {
        System.out.println("LOG: " + event);
    }
}
