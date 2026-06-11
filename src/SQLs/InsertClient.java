package SQLs;

import Database.DataBaseConnection;
import Utilities.Warnings;
import java.sql.*;

public class InsertClient {
    
    public static void insertClients(String client, String dataForMySQL, String street, String city) throws ClassNotFoundException {
        
        String SQL = "INSERT INTO Customer (CustName, DOB, Street, City) VALUES (?, ?, ?, ?)";

        try {
            // GET THE SHARED DATABASE CONNECTION. DO NOT PUT THIS INSIDE THE TRY BLOCK.
            Connection conX = DataBaseConnection.getConnection();
            
            // PUT ONLY THE PREPARED STATEMENT INSIDE THE TRY BLOCK.
            // THIS CLOSES THE STATEMENT AUTOMATICALLY WHEN IT FINISHES BUT KEEPS THE CONNECTION OPEN.
            try (PreparedStatement PST = conX.prepareStatement(SQL)) {
                 
                // SET THE VALUES FOR THE SQL QUERY
                PST.setString(1, client);
                PST.setString(2, dataForMySQL);
                PST.setString(3, street);
                PST.setString(4, city);

                // EXECUTE THE INSERT AND CHECK HOW MANY ROWS WERE AFFECTED
                int linhasAfetadas = PST.executeUpdate();

                if (linhasAfetadas > 0) {
                    // THE INSERT WAS SUCCESSFUL
                    
                } else {
                    // IF NO ROWS WERE AFFECTED, SHOW AN ERROR WARNING
                    Warnings.insertClientFailed();
                }
            }
            
        } catch (SQLException e) {
            // CATCH AND SHOW ANY DATABASE ERRORS
            System.out.println("error inserting data: " + e.getMessage());
        }
    }
}