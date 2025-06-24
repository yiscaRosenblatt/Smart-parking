package main.observer;

public class EntryLogger implements ParkingObserver {
    @Override
    public void update(String event) {
        System.out.println("LOG: " + event);
    }
}
