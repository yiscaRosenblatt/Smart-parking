package main.model;

/**
 * מייצגת שער כניסה או יציאה מהחניון.
 * כל שער שומר את מצבו (פתוח/סגור) ומדמה את פעולת הפתיחה והסגירה.
 */
public class Gate {
    private boolean isOpen;

    /**
     * בונה שער חדש כשהוא במצב סגור כברירת מחדל.
     */
    public Gate() {
        this.isOpen = false;
    }

    /**
     * פותח את השער – משנה את המצב הפנימי ומדפיס הודעה מתאימה.
     */
    public void open() {
        isOpen = true;
        System.out.println("Gate is now OPEN.");
    }

    /**
     * סוגר את השער – משנה את המצב הפנימי ומדפיס הודעה מתאימה.
     */
    public void close() {
        isOpen = false;
        System.out.println("Gate is now CLOSED.");
    }

    /**
     * בודק האם השער פתוח.
     *
     * @return true אם השער פתוח, אחרת false.
     */
    public boolean isOpen() {
        return isOpen;
    }
}
