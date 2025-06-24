// Main.java
package main;

import main.model.*;
import main.observer.EntryLogger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ParkingLot lot = new ParkingLot();
        lot.addSpot("Regular", "R1");
        lot.addSpot("Regular", "R2");
        lot.addSpot("Handicapped", "H1");

        lot.addObserver(new EntryLogger());

        boolean running = true;
        while (running) {
            System.out.println("\nבחר פעולה:\n1. כניסת רכב\n2. יציאה\n3. הצג חניות פנויות\n4. יציאה מהמערכת");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("הכנס שם נהג: ");
                    String name = scanner.nextLine();
                    System.out.print("הכנס סוג רכב (car/motorcycle): ");
                    String type = scanner.nextLine();
                    System.out.print("הכנס מספר רישוי: ");
                    String plate = scanner.nextLine();

                    Vehicle v = type.equalsIgnoreCase("motorcycle") ?
                            new Motorcycle(plate, name,true) :
                            new Car(plate, name);

                    lot.enterVehicle(v);
                    break;

                case "2":
                    System.out.print("הכנס מספר רישוי לרכב שיצא: ");
                    String plateOut = scanner.nextLine();
                    lot.exitVehicle(plateOut);
                    break;

                case "3":
                    System.out.println("חניות פנויות:");
                    for (ParkingSpot s : lot.getAvailableSpots()) {
                        System.out.println(" - " + s.getSpotId() + " (" + s.getType() + ")");
                    }
                    break;

                case "4":
                    running = false;
                    System.out.println("להתראות!");
                    break;

                default:
                    System.out.println("בחירה לא חוקית. נסה שוב.");
            }
        }
    }
}
