package SQLs;

import Database.DataBaseConnection;
import java.sql.*;

/**
 * THIS CLASS SAVES A NEW BOOKING TO THE DATABASE.
 * IT CREATES THE 'BOOKING' RECORD FIRST, GETS THE NEW ID,
 * AND THEN LINKS THE SERVICE IN THE 'BOOKINGSERVICE' TABLE.
 */
public class InsertBooking {
    
    public static void saveBooking(int CustomerID, int CarTypeID, int ServiceID, String DateOfBooking) throws SQLException {
        // SQL QUERIES TO PUT DATA INTO TWO DIFFERENT TABLES
        String insertBooking = "INSERT INTO Booking (CustomerID, CarTypeID, DateOfBooking) VALUES (?, ?, ?)";
        String insertService = "INSERT INTO BookingService (BookingID, ServiceID) VALUES (?, ?)";
        
        try {
            // GET THE SHARED DATABASE CONNECTION
            Connection conX = DataBaseConnection.getConnection();
            
            /* * USE RETURN_GENERATED_KEYS TO GET THE NEW BOOKING ID.
             * THE CODE NEEDS THIS ID TO USE IT IN THE BOOKINGSERVICE TABLE
             * IMMEDIATELY AFTER THE FIRST INSERT.
             */
            try (PreparedStatement PSBooking = conX.prepareStatement(insertBooking, Statement.RETURN_GENERATED_KEYS)) {
                PSBooking.setInt(1, CustomerID);
                PSBooking.setInt(2, CarTypeID);
                PSBooking.setString(3, DateOfBooking);
                
                // EXECUTE THE INSERT TO SAVE THE BOOKING
                PSBooking.executeUpdate();
                
                // GET THE NEW ID FROM THE DATABASE FOR THE ROW IT JUST CREATED
                try (ResultSet myGeneratedKeys = PSBooking.getGeneratedKeys()) {
                    int newBookingID = 0;
                    if (myGeneratedKeys.next()) {
                        newBookingID = myGeneratedKeys.getInt(1);
                    }
                    
                    // LINK THE SERVICE TO THE BOOKING.
                    // IF THE ID IS VALID, SAVE THE SERVICE DETAILS.
                    if (newBookingID > 0) {
                        try (PreparedStatement PSService = conX.prepareStatement(insertService)) {
                            PSService.setInt(1, newBookingID);
                            PSService.setInt(2, ServiceID);
                            
                            PSService.executeUpdate();
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            // CATCH AND HANDLE PROBLEMS WITH DATABASE DRIVERS OR CONNECTION SETTINGS
            System.out.println("\n[ERROR] Failed to save booking: " + e.getMessage());
        }
    }
}