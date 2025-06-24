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
            System.out.println("\nבחר פעולה:\n1. כניסת רכב\n2. יציאה\n3. הצג חניות פנויות\n4. יציאה מהמערכת\n5. הדפסת דוח סטטיסטי");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("הכנס שם נהג: ");
                    String name;
                    while (true) {
                        System.out.print("הכנס שם נהג: ");
                        name = scanner.nextLine().trim();
                        if (name.matches("[a-zA-Zא-ת ]+")) {
                            break;
                        } else {
                            System.out.println("השם חייב להכיל אותיות בלבד (עברית או אנגלית), ללא מספרים.");
                        }
                    }

                    String type;
                    while (true) {
                        System.out.print("הכנס סוג רכב (car/motorcycle): ");
                        type = scanner.nextLine().trim().toLowerCase();
                        if (type.equals("car") || type.equals("motorcycle")) {
                            break;
                        } else {
                            System.out.println("סוג רכב לא תקין. נא לבחור בין 'car' או 'motorcycle'.");
                        }
                    }

                    String plate;
                    while (true) {
                        System.out.print("הכנס מספר רישוי: ");
                        plate = scanner.nextLine().trim();
                        if (plate.matches("\\d+")) {  // רק ספרות
                            break;
                        } else {
                            System.out.println("מספר רישוי חייב להכיל ספרות בלבד.");
                        }
                    }

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

                case "5":
                    lot.getRevenueReport().printReport();
                    break;


                default:
                    System.out.println("בחירה לא חוקית. נסה שוב.");
            }
        }
    }
}
