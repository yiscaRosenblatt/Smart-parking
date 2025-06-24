// Main.java
package main;

import UI.ParkingAppUI;
import main.model.*;
import main.observer.EntryLogger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.addSpot("Regular", "R1");
        parkingLot.addSpot("Regular", "R2");
        parkingLot.addSpot("Regular", "R3");
        parkingLot.addSpot("Handicapped", "H1");
        parkingLot.addSpot("Handicapped", "H2");
        ParkingAppUI app = new ParkingAppUI(parkingLot);
        app.run();
    }
}



