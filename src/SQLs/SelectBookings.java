package SQLs;

import Database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * THIS CLASS RETRIEVES AND DISPLAYS ALL ACTIVE BOOKINGS IN THE SYSTEM.
 * IT USES A SINGLETON CONNECTION TO MAINTAIN ONE OPEN BRIDGE TO THE DATABASE.
 */
public class SelectBookings {

    public static void selectBookings() throws ClassNotFoundException, SQLException {

        // THE QUERY USES JOINS TO COMBINE TABLES. THIS PROVIDES REAL NAMES INSTEAD OF JUST ID NUMBERS.
        String query = "SELECT B.BookingID, C.CustName, T.CarTypeName, S.ServiceName, B.DateOfBooking, (T.BaseRent + S.ServiceCharge) AS Total "
                + "FROM Booking B "
                + "JOIN Customer C ON B.CustomerID = C.CustomerID "
                + "JOIN CarType T ON B.CarTypeID = T.CarTypeID "
                + "JOIN BookingService BS ON B.BookingID = BS.BookingID "
                + "JOIN Service S ON BS.ServiceID = S.ServiceID";

        // GET THE SHARED DATABASE CONNECTION. DO NOT CLOSE THIS CONNECTION HERE.
        Connection con = DataBaseConnection.getConnection();

        /*
         * USE TRY-WITH-RESOURCES:
         * PLACING THE PREPAREDSTATEMENT AND RESULTSET HERE ALLOWS JAVA TO AUTOMATICALLY
         * CLOSE THEM WHEN THE DATA FINISHES PRINTING. THIS PREVENTS THE DATABASE
         * FROM KEEPING CONNECTIONS OPEN BY MISTAKE.
         */
        try (PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            
            // PRINT THE TABLE HEADER WITH FIXED FORMATS
            System.out.printf("%-5s | %-15s | %-15s | %-15s | %-12s | %-8s%n",
                    "ID", "CLIENT", "VEHICLE", "SERVICE", "DATE", "TOTAL");
            System.out.println("-------------------------------------------------------------------------------------");

            // LOOP THROUGH EVERY ROW RETURNED BY THE DATABASE
            while (rs.next()) {
                System.out.printf("%-5d | %-15s | %-15s | %-15s | %-12s | $%.2f%n",
                        rs.getInt("BookingID"),
                        rs.getString("CustName"),
                        rs.getString("CarTypeName"),
                        rs.getString("ServiceName"),
                        rs.getString("DateOfBooking"),
                        rs.getDouble("Total")
                );
            }
        } catch (SQLException e) {
            // PRINT A CLEAN ERROR MESSAGE IN CASE OF A DATABASE ISSUE
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}