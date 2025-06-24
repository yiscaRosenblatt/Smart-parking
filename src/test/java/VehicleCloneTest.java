package test.java;

import main.model.Car;

public class VehicleCloneTest {
    public static void main(String[] args) {
        Car original = new Car("1234567", "yisca");
        Car copy = (Car) original.clone();

        System.out.println("Original plate: " + original.getLicensePlate());
        System.out.println("Copy plate: " + copy.getLicensePlate());

        // בדיקה אם זה אובייקט אחר בזיכרון
        System.out.println("Same object? " + (original == copy)); // אמור להיות false
        System.out.println("Same plate? " + original.getLicensePlate().equals(copy.getLicensePlate())); // true
    }
}
