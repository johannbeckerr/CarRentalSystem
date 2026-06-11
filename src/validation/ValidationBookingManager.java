package validation;

import SQLs.SelectClients;
import SQLs.SelectManagerBooking;
import SQLs.SelectServices;
import SQLs.SelectVehicles;
import static Utilities.Colors.BLACK;
import static Utilities.Colors.CYAN;
import static Utilities.Colors.GREEN;
import static Utilities.Colors.RESET;
import Utilities.Warnings;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.sql.SQLException;

/**
 * THIS CLASS HANDLES INPUT VALIDATION FOR THE BOOKING MANAGEMENT SYSTEM.
 * IT CHECKS IF IDs EXIST IN THE DATABASE AND VALIDATES DATE FORMATS.
 */
public class ValidationBookingManager {
    
    /**
     * REQUESTS AND VALIDATES THE CLIENT ID FROM THE USER.
     * DISPLAYS THE LIST OF CUSTOMERS UNTIL A VALID ID IS PROVIDED OR CANCELED.
     */
    public static int captureValidClientID(Scanner scanner) throws SQLException, ClassNotFoundException {
        int id = -1;
        boolean isValidAndExists = false;

        do {
            System.out.println("\n");
            System.out.println(BLACK + "CLIENTS      ⚪" + RESET);
            System.out.println(BLACK + "CAR MODEL    ⚪" + RESET);
            System.out.println(BLACK + "SERVICE TYPE ⚪" + RESET);
            System.out.println(BLACK + "BOOKING DATE ⚪" + RESET);

            System.out.println(CYAN + "\nREGISTERED CUSTOMERS" + RESET);
            SelectClients.selectClients();
            Warnings.promptCancel();

            System.out.print("\nENTER THE CLIENT ID: ");
            String input = scanner.nextLine().trim();

            // RETURN ZERO IF THE USER DECIDES TO CANCEL
            if (input.equals("0")) {
                return 0;
            }

            try {
                id = Integer.parseInt(input);
                boolean exists = SelectManagerBooking.customerExists(id);

                // CHECK IF THE ID IS PRESENT IN THE DATABASE
                if (exists) {
                    isValidAndExists = true;
                } else {
                    Warnings.iDclientNotFound();
                }
            } catch (NumberFormatException e) {
                // SHOW AN ERROR IF THE INPUT IS NOT A VALID NUMBER
                Warnings.updateClientFailed();
            }
        } while (!isValidAndExists);

        return id;
    }

    /**
     * REQUESTS AND VALIDATES THE VEHICLE ID FROM THE USER.
     * DISPLAYS THE LIST OF VEHICLES UNTIL A VALID ID IS PROVIDED OR CANCELED.
     */
    public static int captureValidVehicleID(Scanner scanner) throws SQLException, ClassNotFoundException {
        int id = -1;
        boolean isValidAndExists = false;

        do {
            System.out.println("\n");
            System.out.println("CLIENTS      ✅");
            System.out.println(BLACK + "CAR MODEL    ⚪" + RESET);
            System.out.println(BLACK + "SERVICE TYPE ⚪" + RESET);
            System.out.println(BLACK + "BOOKING DATE ⚪" + RESET);

            System.out.println(CYAN + "\nREGISTERED CAR MODELS" + RESET);
            SelectVehicles.selectVehicles();
            Warnings.promptCancel();

            System.out.print("\nENTER THE VEHICLE ID: ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return 0;
            }

            try {
                id = Integer.parseInt(input);
                boolean exists = SelectManagerBooking.vehicleExists(id);

                // CHECK IF THE VEHICLE ID IS PRESENT IN THE DATABASE
                if (exists) {
                    isValidAndExists = true;
                } else {
                    Warnings.vehicleNotFoundID();
                }
            } catch (NumberFormatException e) {
                Warnings.updateClientFailed();
            }
        } while (!isValidAndExists);

        return id;
    }

    /**
     * REQUESTS AND VALIDATES THE SERVICE ID FROM THE USER.
     * DISPLAYS THE LIST OF SERVICES UNTIL A VALID ID IS PROVIDED OR CANCELED.
     */
    public static int captureValidServiceID(Scanner scanner) throws SQLException, ClassNotFoundException {
        int id = -1;
        boolean isValidAndExists = false;

        do {
            System.out.println("\n");
            System.out.println("CLIENTS      ✅");
            System.out.println("CAR MODEL    ✅");
            System.out.println(BLACK + "SERVICE TYPE ⚪" + RESET);
            System.out.println(BLACK + "BOOKING DATE ⚪" + RESET);
            
            System.out.println(CYAN + "\nREGISTERED SERVICES TYPES" + RESET);
            SelectServices.selectServices();
            Warnings.promptCancel();

            System.out.print("\nENTER THE SERVICE ID: ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return 0;
            }

            try {
                id = Integer.parseInt(input);
                boolean exists = SelectManagerBooking.serviceExists(id);

                // CHECK IF THE SERVICE ID IS PRESENT IN THE DATABASE
                if (exists) {
                    isValidAndExists = true;
                } else {
                    Warnings.ServiceNotFoundID();
                }
            } catch (NumberFormatException e) {
                Warnings.updateClientFailed();
            }
        } while (!isValidAndExists);

        return id;
    }

    /**
     * REQUESTS AND VALIDATES THE BOOKING DATE FROM THE USER.
     * ENSURES THE DATE FORMAT IS CORRECT AND CONVERTS IT TO DATABASE FORMAT.
     */
    public static String captureValidBookingDate(Scanner scanner) {
        String dob;
        String dataForMySQL = "";
        boolean validDate = false;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            System.out.println("\n");
            System.out.println("CLIENTS      ✅");
            System.out.println("CAR MODEL    ✅");
            System.out.println("SERVICE TYPE ✅");
            System.out.println(BLACK + "BOOKING DATE ⚪" + RESET);

            Warnings.promptCancel();
            System.out.println("");
            System.out.print("\nBOOKING DATE: ");
            dob = scanner.nextLine().trim();

            if (dob.equals("0")) {
                return null;
            }

            if (dob.isEmpty()) {
                Warnings.fielsMandatory();
                continue;
            }
            try {
                // PARSE AND STANDARDIZE THE DATE FOR MYSQL FORMAT (YYYY-MM-DD)
                LocalDate myDate = LocalDate.parse(dob, inputFormat);
                dataForMySQL = myDate.toString(); 
                validDate = true;
            } catch (DateTimeParseException e) {
                // SHOW ERROR IF DATE FORMAT IS INCORRECT
                Warnings.invalidDate();
            }
        } while (!validDate);

        return dataForMySQL;
    }
    
    /**
     * REQUESTS AND VALIDATES THE BOOKING ID TO BE DELETED.
     * CHECKS IF THE ID EXISTS IN THE DATABASE.
     */
    public static int captureValidBookingIDToDelete(Scanner scanner) throws SQLException, ClassNotFoundException {
        int id = -1;
        boolean isValidAndExists = false;

        do {
            Warnings.promptCancel();
            System.out.print("\nENTER THE BOOKING ID TO CANCEL: ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                return 0;
            }

            try {
                id = Integer.parseInt(input);
                boolean exists = SelectManagerBooking.bookingExists(id);

                // CHECK IF THE BOOKING ID EXISTS BEFORE ATTEMPTING DELETION
                if (exists) {
                    isValidAndExists = true;
                } else {
                    Warnings.BookingNotFoundID();
                }
            } catch (NumberFormatException e) {
                Warnings.updateClientFailed();
            }
        } while (!isValidAndExists);

        return id;
    }
}