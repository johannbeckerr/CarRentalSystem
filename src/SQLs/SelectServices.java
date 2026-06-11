package SQLs;

import Database.DataBaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * THIS CLASS GETS AND SHOWS THE AVAILABLE SERVICES IN THE SYSTEM.
 * IT USES TRY-WITH-RESOURCES TO OPEN AND CLOSE DATABASE RESOURCES SAFELY 
 * TO PREVENT MEMORY PROBLEMS.
 */
public class SelectServices {
    
    public static void selectServices() throws ClassNotFoundException, SQLException {
        // THIS QUERY POINTS TO THE SERVICE TABLE TO GET THE DATA
        String selectQuery = "SELECT * FROM CarRentalCCT.Service;";

        // GET THE SHARED DATABASE CONNECTION
        Connection conX = DataBaseConnection.getConnection();

        /*
         * USE TRY-WITH-RESOURCES:
         * PUT THE STATEMENT AND RESULTSET HERE. THIS MAKES SURE THAT 
         * DATABASE RESOURCES CLOSE AUTOMATICALLY WHEN THE READING IS FINISHED, 
         * EVEN IF AN ERROR HAPPENS.
         */
        try (Statement ST = conX.createStatement();
             ResultSet myResults = ST.executeQuery(selectQuery)) {

            // PRINT THE TABLE HEADER WITH PROPER FORMATTING
            System.out.println("\n=====================================================================");
            System.out.printf("%-5s | %-20s | %-15s%n", "SERVICE ID", "SERVICE TYPE", "PRICE");
            System.out.println("---------------------------------------------------------------------");

            // LOOP THROUGH EVERY ROW FOUND IN THE SERVICE TABLE
            while (myResults.next()) {
                // GET THE DATA FROM THE DATABASE
                int id = myResults.getInt("ServiceID");
                String service = myResults.getString("ServiceName");
                String price = myResults.getString("ServiceCharge");

                // PRINT THE DATA FOR EACH ROW AND MAKE SURE IT ALIGNS WITH THE HEADER
                System.out.printf("%-5d | %-20s | %-15s%n", id, service, price);
            }

            System.out.println("=====================================================================\n");

        } catch (SQLException e) {
            // CATCH AND PRINT ANY DATABASE ERRORS CLEARLY
            System.out.println("[!] Database Error: " + e.getMessage());
        }
    }
}