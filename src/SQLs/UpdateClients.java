package SQLs;

import Database.DataBaseConnection;
import Utilities.Warnings;
import java.sql.*;

/**
 * THIS CLASS UPDATES A CLIENT'S INFORMATION IN THE DATABASE.
 * IT USES THE CLIENT ID, A NEW NAME, AND A NEW CITY TO MAKE THE CHANGES.
 */
public class UpdateClients {

    public static void updateClients(int id, String newName, String newCity) throws SQLException, ClassNotFoundException {
        // PREPARE THE SQL COMMAND TO UPDATE THE NAME AND CITY FOR A SPECIFIC CUSTOMER ID
        String sql = "UPDATE Customer SET CustName = ?, City = ? WHERE CustomerID = ?";
        
        // GET THE SHARED DATABASE CONNECTION
        Connection conX = DataBaseConnection.getConnection();

        // USE TRY-WITH-RESOURCES SO THE STATEMENT CLOSES AUTOMATICALLY WHEN THE PROCESS FINISHES
        try (PreparedStatement PST = conX.prepareStatement(sql)) {
            
            // SET THE NEW NAME, NEW CITY, AND THE CUSTOMER ID FOR THE SQL COMMAND
            PST.setString(1, newName);
            PST.setString(2, newCity);
            PST.setInt(3, id);

            // EXECUTE THE UPDATE COMMAND AND CHECK HOW MANY ROWS WERE CHANGED IN THE DATABASE
            int linhasAfetadas = PST.executeUpdate();

            if (linhasAfetadas > 0) {
                // IF AT LEAST ONE ROW WAS CHANGED, THE UPDATE WAS SUCCESSFUL
                // WARNINGS.UPDATECLIENT();
            } else {
                // IF NO ROWS WERE CHANGED, SHOW A WARNING THAT THE ID WAS NOT FOUND
                Warnings.iDclientNotFound();
            }
        } catch (SQLException e) {
            // CATCH AND PRINT ANY DATABASE ERRORS CLEARLY
            System.out.println("Update Error: " + e.getMessage());
        }
    }
}