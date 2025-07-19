package test.java1;

import main.model.Car;
import main.model.ParkingLot;
import main.model.Vehicle;
import main.util.ParkingFeeCalculator;


import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class ParkingLotTest {

    // בדיקת כניסת רכב - האם התווסף לרשימת הרכבים?
    @Test
    public void testVehicleEntry() {
        ParkingLot lot = new ParkingLot();
        lot.addSpot("regular", "A1");

        Vehicle car = new Car("1234567", "יסכה");
        lot.enterVehicle(car);

        assertEquals(1, lot.getVehicles().size());
        assertEquals("1234567", lot.getVehicles().get(0).getLicensePlate());
    }

    // בדיקת יציאת רכב - האם הועבר לרשימת exitedVehicles
    @Test
    public void testVehicleExit() {
        ParkingLot lot = new ParkingLot();
        lot.addSpot("regular", "B1");

        Vehicle car = new Car("9876543", "רותם");
        lot.enterVehicle(car);

        lot.exitVehicle("9876543");

        assertEquals(0, lot.getVehicles().size());
        assertEquals(1, lot.getExitedVehicles().size());
        assertEquals("9876543", lot.getExitedVehicles().get(0).getLicensePlate());
    }

    // בדיקה שהשכפול יוצר אובייקט חדש עם אותם פרטים
    @Test
    public void testCloneCreatesNewInstance() {
        Car original = new Car("7777777", "דנה");
        Car copy = (Car) original.clone();

        assertNotSame(original, copy); // מצביעים שונים
        assertEquals(original.getLicensePlate(), copy.getLicensePlate());
    }

    // בדיקת חישוב מחיר חניה
    @Test
    public void testParkingFeeCalculation() {
        Vehicle v = new Car("2468101", "טל");
        v.setEntryTime(LocalDateTime.now().minusMinutes(60));
        v.setExitTime(LocalDateTime.now());

        double fee = ParkingFeeCalculator.calculate(v, 5.0);
        assertEquals(300.0, fee); // 60 דקות * 5
    }

    // בדיקה כשאין מקומות פנויים
    @Test
    public void testNoAvailableSpots() {
        ParkingLot lot = new ParkingLot();
        lot.addSpot("regular", "C1");

        lot.enterVehicle(new Car("1000000", "ניסים")); // תופס את המקום
        lot.enterVehicle(new Car("1000001", "עודד"));  // אין מקום פנוי

        assertEquals(1, lot.getVehicles().size()); // רק רכב אחד נכנס
    }
}
