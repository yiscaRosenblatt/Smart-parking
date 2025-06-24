package main.model;


public class Car extends Vehicle {

    public Car(String licensePlate, String ownerName) {
        super(licensePlate, ownerName);
    }

    @Override
    public Vehicle clone() {
        return new Car(this.licensePlate, this.ownerName);
    }
}
