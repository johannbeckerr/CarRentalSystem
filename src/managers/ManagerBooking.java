package managers;

import SQLs.DeleteBooking;
import SQLs.InsertBooking;
import SQLs.SelectBookings;
import SQLs.SelectClients;
import SQLs.SelectManagerBooking;
import SQLs.SelectServices;
import SQLs.SelectVehicles;
import Utilities.ClearScreen;
import static Utilities.Colors.CYAN;
import static Utilities.Colors.RESET;
import Utilities.Warnings;
import java.sql.SQLException;
import java.util.Scanner;
import validation.ValidationBookingManager;

/**
 * THIS CLASS MANAGES THE BOOKING PROCESS.
 * IT HANDLES CREATING NEW BOOKINGS, DELETING EXISTING ONES, AND VIEWING THE BOOKING LIST.
 */
public class ManagerBooking {

    public static void allCapture() throws SQLException, ClassNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // GET A VALID CLIENT ID
        int clientID = ValidationBookingManager.captureValidClientID(scanner);
        
        // IF THE ID IS 0, THE PROCESS IS CANCELLED
        if (clientID == 0) {
            Warnings.cancelRegistration();
            return;
        }
        ClearScreen.clearScreen();
        
        // GET A VALID VEHICLE ID
        int vehicleID = ValidationBookingManager.captureValidVehicleID(scanner);
        ClearScreen.clearScreen();
        if (vehicleID == 0) {
            Warnings.cancelRegistration();
            return;
        }
        ClearScreen.clearScreen();
        
        // GET A VALID SERVICE ID
        int serviceID = ValidationBookingManager.captureValidServiceID(scanner);
        ClearScreen.clearScreen();
        if (serviceID == 0) {
            Warnings.cancelRegistration();
            return;
        }
        ClearScreen.clearScreen();
        
        // GET A VALID DATE
        String bookingDate = ValidationBookingManager.captureValidBookingDate(scanner);
        if (bookingDate == null) {
            Warnings.cancelRegistration();
            return;
        }
        ClearScreen.clearScreen();
        
        // SHOW A SUMMARY OF THE BOOKING BEFORE SAVING
        System.out.println("\n");
        System.out.println("CLIENTS      ✅");
        System.out.println("CAR MODEL    ✅");
        System.out.println("SERVICE TYPE ✅");
        System.out.println("BOOKING DATE ✅");
            
        SelectManagerBooking.showCaptureBooking(clientID, vehicleID, serviceID, bookingDate);
        SelectManagerBooking.showPriceBooking(vehicleID, serviceID);

        // ASK TO SAVE THE BOOKING
        Warnings.saveBooking();
        scanner.nextLine();

        // SAVE THE NEW BOOKING TO THE DATABASE
        InsertBooking.saveBooking(clientID, vehicleID, serviceID, bookingDate);
        
        // CONFIRM THE PROCESS IS DONE
        Warnings.bookingConfirmed();
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void deleteBooking() throws SQLException, ClassNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n========================================");
        System.out.println("             CANCEL BOOKING             ");
        System.out.println("========================================");

        // SHOW ALL CURRENT BOOKINGS TO HELP THE USER CHOOSE
        System.out.println("\n--- CURRENT BOOKINGS ---");
        SelectBookings.selectBookings();

        // GET THE BOOKING ID TO BE DELETED
        int bookingID = ValidationBookingManager.captureValidBookingIDToDelete(scanner);
        if (bookingID == 0) {
            Warnings.cancelRegistration();
            return; 
        }

        // SHOW THE SELECTED BOOKING TO LET THE USER VERIFY IT
        SelectManagerBooking.showOneBookingSelected(bookingID);

        // REQUEST FINAL CONFIRMATION FROM THE USER
        Warnings.bookingCancel();
        String confirm = scanner.nextLine().trim();
        
        // IF THE USER CONFIRMS WITH '1', PERFORM THE DELETION
        if (confirm.equals("1")) {
            DeleteBooking.deleteBooking(bookingID);
            Warnings.bookingCanceled();
        } else {
            Warnings.cancelRegistration();
        }

        // RETURN TO THE MENU
        Warnings.goBack();

    }

    public static void viewAllBookings() throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n========================================");
        System.out.println("              ALL BOOKINGS              ");
        System.out.println("========================================");
        
        // PRINT THE LIST OF ALL BOOKINGS
        SelectBookings.selectBookings();
        
        System.out.println("\n--- END OF LIST ---");
        Warnings.goBack();
    }
}