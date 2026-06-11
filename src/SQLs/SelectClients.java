package SQLs;

import Utilities.DateFormatter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Database.DataBaseConnection;

public class SelectClients {
    
    public static void selectClients() throws ClassNotFoundException, SQLException {
        
        // GET THE SHARED CONNECTION FIRST OUTSIDE THE TRY BLOCK
        Connection conX = DataBaseConnection.getConnection();
        
        String selectQuery = "SELECT * FROM CarRentalCCT.Customer";

        /*
         * USE TRY-WITH-RESOURCES FOR STATEMENTS AND RESULT SETS.
         * PUTTING THE STATEMENT AND RESULT SET HERE ALLOWS JAVA TO CLOSE THEM 
         * AUTOMATICALLY WHEN THE LOOP FINISHES. THIS PREVENTS MEMORY PROBLEMS.
         * THE DATABASE CONNECTION REMAINS OPEN FOR OTHER CLASSES TO USE.
         */
        try (Statement ST = conX.createStatement();
             ResultSet myResults = ST.executeQuery(selectQuery)) {

            // PRINT THE TABLE HEADER
            System.out.println("\n======================================================================================");
            System.out.printf("%-5s | %-20s | %-15s | %-24s | %-15s %n", "ID", "CLIENT NAME", "DATE OF BIRTH", "STREET", "CITY");
            System.out.println("--------------------------------------------------------------------------------------");

            // LOOP THROUGH EVERY ROW RETURNED BY THE DATABASE
            while (myResults.next()) {
                int id = myResults.getInt("CustomerID");
                String name = myResults.getString("CustName");
                String dateFormated = DateFormatter.dateFormatter(myResults.getString("DOB"));
                String street = myResults.getString("Street");
                String city = myResults.getString("City");

                // PRINT THE FORMATTED DATA FOR EACH CLIENT
                System.out.printf("%-5d | %-20s | %-15s | %-25s| %-15s %n", id, name, dateFormated, street, city);
            }
            System.out.println("======================================================================================\n");
            
        } catch (SQLException e) {
            // PRINT A CLEAR ERROR MESSAGE IF THERE IS A DATABASE PROBLEM
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}