package main.model;


public class Car extends Vehicle {

    public Car(String licensePlate, String ownerName) {
        super(licensePlate, ownerName);
    }

    @Override
    public Vehicle clone() {
        Car cloned = new Car(this.licensePlate, this.ownerName);
        cloned.setEntryTime(this.entryTime);
        cloned.setExitTime(this.exitTime);
        return cloned;
    }
}

