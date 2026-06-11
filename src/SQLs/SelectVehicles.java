package SQLs;

import Database.DataBaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * THIS CLASS GETS AND SHOWS THE AVAILABLE VEHICLE TYPES FROM THE DATABASE.
 * IT USES A SINGLETON CONNECTION AND TRY-WITH-RESOURCES TO KEEP THE CODE 
 * SAFE AND PREVENT MEMORY PROBLEMS.
 */
public class SelectVehicles {

    public static void selectVehicles() throws ClassNotFoundException, SQLException {
        // THIS QUERY GETS ALL VEHICLE TYPES FROM THE DATABASE
        String selectQuery = "SELECT * FROM CarRentalCCT.CarType;";

        // GET THE SHARED DATABASE CONNECTION
        Connection conX = DataBaseConnection.getConnection();

        /*
         * USE TRY-WITH-RESOURCES:
         * PUT THE STATEMENT AND RESULTSET HERE. THIS MAKES SURE THAT 
         * DATABASE RESOURCES CLOSE IMMEDIATELY WHEN THE DATA PROCESSING 
         * IS FINISHED, KEEPING THE APPLICATION MEMORY EFFICIENT.
         */
        try (Statement ST = conX.createStatement();
             ResultSet myResults = ST.executeQuery(selectQuery)) {

            // PRINT THE TABLE HEADER WITH PROPER FORMATTING
            System.out.println("\n=====================================================================");
            System.out.printf("%-5s | %-20s | %-15s%n", "ID", "CAR TYPE", "PRICE");
            System.out.println("---------------------------------------------------------------------");

            // LOOP THROUGH EVERY ROW FOUND IN THE CARTYPE TABLE
            while (myResults.next()) {
                // GET THE DATA FROM THE DATABASE
                int id = myResults.getInt("CarTypeID");
                String car = myResults.getString("CarTypeName");
                String rent = myResults.getString("BaseRent");

                // PRINT THE DATA FOR EACH ROW AND MAKE SURE IT ALIGNS WITH THE HEADER
                System.out.printf("%-5d | %-20s | %-15s%n", id, car, rent);
            }

            System.out.println("=====================================================================\n");

        } catch (SQLException e) {
            // CATCH AND PRINT ANY DATABASE ERRORS CLEARLY
            System.out.println("[!] Database Error: " + e.getMessage());
        }
    }
}