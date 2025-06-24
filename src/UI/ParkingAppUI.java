package UI;


import main.model.Car;
import main.model.Motorcycle;
import main.model.ParkingLot;
import main.model.Vehicle;
import main.util.ParkingFeeCalculator;

import java.time.Duration;
import java.util.Scanner;

public class ParkingAppUI {
    private final ParkingLot parkingLot;
    private final Scanner scanner;

    public ParkingAppUI(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to the Yisca Parking Lot!");
        System.out.println("Price per minute: " + parkingLot.getPricePerMinute() + " ₪");

        int choice;
        do {
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    handleVehicleEntry();
                    break;
                case 2:
                    handleVehicleExit();
                    break;
                case 3:
                    parkingLot.getRevenueReport().printReport();
                    break;
                case 4:
                    printVehicleHistory();
                    break;
                case 0:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. כניסה לחניון");
        System.out.println("2. יציאה מהחניון");
        System.out.println("3. הדפסת דוח");
        System.out.println("4. צפייה בהיסטוריית רכבים");
        System.out.println("0. יציאה");
        System.out.print("בחר אופציה: ");
    }

    private void handleVehicleEntry() {
        System.out.print("הכנס שם נהג: ");
        String name = scanner.nextLine();
        while (!name.matches("[a-zA-Zא-ת]+")) {
            System.out.print("שם לא חוקי. הכנס שם המכיל רק אותיות: ");
            name = scanner.nextLine();
        }

        System.out.print("הכנס סוג רכב (car/motorcycle): ");
        String type = scanner.nextLine().toLowerCase();
        while (!type.equals("car") && !type.equals("motorcycle")) {
            System.out.print("סוג לא חוקי. הכנס car או motorcycle: ");
            type = scanner.nextLine().toLowerCase();
        }

        System.out.print("הכנס מספר רישוי (ספרות בלבד): ");
        String plate = scanner.nextLine();
        while (!plate.matches("\\d+")) {
            System.out.print("מספר רישוי לא חוקי. נסה שוב: ");
            plate = scanner.nextLine();
        }

        Vehicle vehicle;

        switch (type.toLowerCase()) {
            case "car":
                vehicle = new Car(plate, name);
                break;
            case "motorcycle":
                vehicle = new Motorcycle(plate, name,true);
                break;
            default:
                System.out.println("סוג רכב לא תקין. אנא הזן 'car' או 'motorcycle'.");
                return; // עוצרים את הפעולה
        }

        parkingLot.enterVehicle(vehicle);

    }

    private void handleVehicleExit() {
        System.out.print("הכנס מספר רישוי של הרכב שיוצא: ");
        String plate = scanner.nextLine();
        parkingLot.exitVehicle(plate);
    }

    private void printVehicleHistory() {
        System.out.println("--- Vehicle History ---");
        for (Vehicle v : parkingLot.getExitedVehicles()) {
            long duration = Duration.between(v.getEntryTime(), v.getExitTime()).toMinutes();
            double fee = ParkingFeeCalculator.calculate(v, parkingLot.getPricePerMinute());


            System.out.println("רכב: " + v.getLicensePlate());
            System.out.println("בעלים: " + v.getOwnerName());
            System.out.println("משך חניה: " + duration + " דקות");
            System.out.println("תשלום: " + fee + " ₪");
            System.out.println("--------------");
        }
    }
}
