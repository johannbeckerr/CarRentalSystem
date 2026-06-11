package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    
    // CREATE A STATIC VARIABLE TO KEEP A SINGLE DATABASE CONNECTION
    private static Connection oneConnection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        // CHECK IF THE CONNECTION IS NULL OR IF IT IS CLOSED
        if (oneConnection == null || oneConnection.isClosed()) {
            
            // CREATE A NEW CONNECTION IF IT DOES NOT EXIST. 
            // UPDATE THESE DETAILS WITH THE REAL DATABASE INFORMATION.

            String url = "jdbc:mysql://mysql-3c75512e-cctcarrental2026.d.aivencloud.com:28271/CarRentalCCT?sslMode=REQUIRED";
            String user = "avnadmin";
            String password = System.getProperty("DB_PASSWORD");
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            oneConnection = DriverManager.getConnection(url, user, password); 
        }
        
        // RETURN THE OPEN DATABASE CONNECTION
        return oneConnection;
    }
}