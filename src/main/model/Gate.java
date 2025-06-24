package main.model;

public class Gate {
    private boolean isOpen;

    public Gate() {
        this.isOpen = false;
    }

    public void open() {
        isOpen = true;
        System.out.println("Gate is now OPEN.");
    }

    public void close() {
        isOpen = false;
        System.out.println("Gate is now CLOSED.");
    }

    public boolean isOpen() {
        return isOpen;
    }
}
