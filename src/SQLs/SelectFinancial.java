package SQLs;

import Database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * THIS CLASS CALCULATES AND SHOWS THE TOTAL REVENUE FROM ALL BOOKINGS.
 * IT USES JOINS TO ADD PRICES TOGETHER FROM DIFFERENT TABLES.
 */
public class SelectFinancial {

    public static void showTotalFinancial() throws SQLException, ClassNotFoundException {
        // THE SQL QUERY JOINS FOUR TABLES TO CALCULATE THE TOTAL SUM OF THE BASE RENT AND SERVICE CHARGES.
        String query = "SELECT SUM(T.BaseRent + S.ServiceCharge) AS Total FROM Booking B "
                + "JOIN CarType T ON B.CarTypeID = T.CarTypeID "
                + "JOIN BookingService BS ON B.BookingID = BS.BookingID "
                + "JOIN Service S ON BS.ServiceID = S.ServiceID";

        // GET THE SHARED DATABASE CONNECTION. DO NOT CLOSE THIS CONNECTION HERE.
        Connection con = DataBaseConnection.getConnection();

        /*
         * USE TRY-WITH-RESOURCES:
         * THE PREPAREDSTATEMENT AND RESULTSET ARE PUT HERE SO JAVA CLOSES THEM
         * AUTOMATICALLY WHEN THE CALCULATION IS FINISHED. THIS STOPS THE DATABASE
         * FROM RUNNING OUT OF MEMORY.
         */
        try (PreparedStatement PST = con.prepareStatement(query);
             ResultSet rs = PST.executeQuery()) {
            
            // CHECK IF THERE IS DATA TO SHOW.
            if (rs.next()) {
                // GET THE TOTAL SUM FROM THE QUERY RESULT.
                double total = rs.getDouble("Total");

                // FORMAT THE SCREEN OUTPUT.
                System.out.println("\n==================================");
                System.out.println("        FINANCIAL SUMMARY         ");
                System.out.println("==================================");
                
                // PRINT THE TOTAL NUMBER FORMATTED WITH TWO DECIMAL PLACES.
                System.out.printf(" TOTAL REVENUE: $%.2f%n", total);
                System.out.println("==================================");
            }
        } catch (SQLException e) {
            // CATCH AND PRINT ANY DATABASE ERRORS CLEARLY.
            System.out.println("[!] Database Error: " + e.getMessage());
        }
    }
}