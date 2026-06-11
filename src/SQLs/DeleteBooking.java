package SQLs;

import Database.DataBaseConnection;
import Utilities.Warnings;
import java.sql.*;

/**
 * THIS CLASS DELETES A BOOKING.
 * A TRANSACTION KEEPS THE DATA CONSISTENT IN BOTH 
 * THE 'BOOKINGSERVICE' AND 'BOOKING' TABLES.
 */
public class DeleteBooking {

    public static void deleteBooking(int bookingID) throws SQLException, ClassNotFoundException {

        String deleteLink = "DELETE FROM BookingService WHERE BookingID = ?";
        String deleteBooking = "DELETE FROM Booking WHERE BookingID = ?";

        // GET THE SHARED DATABASE CONNECTION
        Connection con = DataBaseConnection.getConnection();
        
        try {
            /* * START TRANSACTION:
             * TURN OFF AUTO-COMMIT SO BOTH DELETE COMMANDS BECOME ONE SINGLE OPERATION.
             * IF THE SECOND COMMAND FAILS, THE FIRST ONE IS NOT SAVED.
             */
            con.setAutoCommit(false);

            // TRY-WITH-RESOURCES:
            // MANAGE THE PREPARED STATEMENTS HERE SO THEY CLOSE SAFELY AUTOMATICALLY.
            try (PreparedStatement PS1 = con.prepareStatement(deleteLink);
                 PreparedStatement PS2 = con.prepareStatement(deleteBooking)) {
                
                // DELETE FROM THE BOOKINGSERVICE TABLE FIRST TO PREVENT FOREIGN KEY ERRORS
                PS1.setInt(1, bookingID);
                PS1.executeUpdate();

                // DELETE FROM THE BOOKING TABLE SECOND
                PS2.setInt(1, bookingID);
                PS2.executeUpdate();

                /*
                 * COMMIT:
                 * IF THERE ARE NO ERRORS, SAVE ALL CHANGES TO THE DATABASE.
                 */
                con.commit();

            } catch (SQLException e) {
                /*
                 * ROLLBACK:
                 * IF THERE IS AN ERROR, UNDO ALL CHANGES TO KEEP THE DATA SAFE.
                 */
                con.rollback();
                System.out.println("\n[ERROR] Could not delete booking. Transaction rolled back.");
                throw e; 
            } finally {
                // RESET AUTO-COMMIT TO TRUE FOR THE NEXT TIME THE CONNECTION IS USED
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("\n[ERROR] Database Transaction Failed: " + e.getMessage());
            throw e;
        }
    }
}