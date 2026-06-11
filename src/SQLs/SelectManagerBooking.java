package SQLs;

import Utilities.DateFormatter;
import Database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * THIS CLASS GETS AND SHOWS INFORMATION FOR THE BOOKING SYSTEM.
 * IT READS A LOT OF DATA, SO MANAGING MEMORY SAFELY IS THE MOST IMPORTANT THING.
 */
public class SelectManagerBooking {

    // --- EXISTENCE CHECKS ---
    // THESE METHODS RETURN 'TRUE' IF AN ID EXISTS, AND 'FALSE' IF IT DOES NOT.
    public static boolean customerExists(int clientID) throws ClassNotFoundException, SQLException {
        // THE 'EXISTS' SQL COMMAND IS FASTER THAN A REGULAR SELECT. 
        // IT STOPS SEARCHING THE DATABASE AS SOON AS IT FINDS ONE MATCHING ROW.
        String query = "SELECT EXISTS(SELECT 1 FROM Customer WHERE CustomerID = ?);";

        // GET THE SHARED SINGLETON CONNECTION THAT STAYS OPEN FOR THE WHOLE APP
        Connection conX = DataBaseConnection.getConnection();

        // OPEN THE PREPAREDSTATEMENT SAFELY. TRY-WITH-RESOURCES CLOSES THE 
        // STATEMENT AUTOMATICALLY TO PREVENT MEMORY PROBLEMS.
        try (PreparedStatement ps = conX.prepareStatement(query)) {

            // PROTECT THE DATABASE FROM SQL INJECTION BY USING THE '?' PLACEHOLDER
            ps.setInt(1, clientID);

            // A SECOND TRY-WITH-RESOURCES BLOCK IS OPENED FOR THE RESULTSET.
            // THIS DESTROYS THE RESULTSET IMMEDIATELY AFTER READING IS FINISHED.
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // GETBOOLEAN(1) RETURNS TRUE IF THE SQL EXISTS COMMAND FOUND A MATCH
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            // PRINT THE ERROR MESSAGE CLEARLY IF THE DATABASE QUERY FAILS
            System.out.println("Database error: " + e.getMessage());
        }
        // RETURN FALSE IF THE ID IS NOT FOUND OR IF THERE IS AN ERROR
        return false;
    }

    public static boolean vehicleExists(int vehicleID) throws ClassNotFoundException, SQLException {
        String query = "SELECT EXISTS(SELECT 1 FROM CarType WHERE CarTypeID = ?);";

        // GET THE DATABASE CONNECTION
        Connection conX = DataBaseConnection.getConnection();
        
        // CHECK IF THE VEHICLE ID EXISTS IN THE DATABASE
        try (PreparedStatement ps = conX.prepareStatement(query)) {
            ps.setInt(1, vehicleID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }

    public static boolean serviceExists(int serviceID) throws ClassNotFoundException, SQLException {
        String query = "SELECT EXISTS(SELECT 1 FROM Service WHERE ServiceID = ?);";

        // GET THE DATABASE CONNECTION
        Connection conX = DataBaseConnection.getConnection();
        
        // CHECK IF THE SERVICE ID EXISTS IN THE DATABASE
        try (PreparedStatement ps = conX.prepareStatement(query)) {
            ps.setInt(1, serviceID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }

    public static boolean bookingExists(int bookingID) throws ClassNotFoundException, SQLException {
        String query = "SELECT EXISTS(SELECT 1 FROM Booking WHERE BookingID = ?);";

        // GET THE DATABASE CONNECTION
        Connection conX = DataBaseConnection.getConnection();
        
        // CHECK IF THE BOOKING ID EXISTS IN THE DATABASE
        try (PreparedStatement ps = conX.prepareStatement(query)) {
            ps.setInt(1, bookingID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false;
    }

    // --- SELECTION DISPLAYS ---
    // THESE METHODS GET SPECIFIC ROWS AND FORMAT THEM NEATLY FOR THE SCREEN.
    public static void showOneClientSelected(int clientID) throws ClassNotFoundException, SQLException {
        String selectQuery = "SELECT * FROM Customer WHERE CustomerID = ?";
        Connection conX = DataBaseConnection.getConnection();

        // PREPARE THE QUERY TO GET THE SPECIFIC CUSTOMER BY THEIR ID
        try (PreparedStatement PST = conX.prepareStatement(selectQuery)) {
            PST.setInt(1, clientID);

            // EXECUTE THE QUERY AND MANAGE THE RESULTSET WITH TRY-WITH-RESOURCES
            try (ResultSet RS = PST.executeQuery()) {
                System.out.println("\n--- SELECTED CLIENT ---");
                System.out.println("=====================================================================");

                // PRINT THE TABLE HEADER USING FORMATTED STRINGS FOR FIXED SPACING
                System.out.printf("%-5s | %-20s | %-15s | %-15s %n", "ID", "CLIENT NAME", "DATE OF BIRTH", "CITY");
                System.out.println("---------------------------------------------------------------------");

                // LOOP THROUGH THE RESULTS TO PRINT THE ROW DETAILS
                while (RS.next()) {
                    int id = RS.getInt("CustomerID");
                    String name = RS.getString("CustName");

                    // USE THE DATEFORMATTER TO CHANGE DATABASE DATE STRINGS INTO A READABLE FORMAT
                    String dateFormated = DateFormatter.dateFormatter(RS.getString("DOB"));
                    String city = RS.getString("City");

                    // PRINT THE DATA ROW AND MAKE SURE IT ALIGNS PERFECTLY WITH THE HEADER
                    System.out.printf("%-5d | %-20s | %-15s | %-15s %n", id, name, dateFormated, city);
                }
                System.out.println("=====================================================================\n");
            }
        } catch (SQLException e) {
            // PRINT A CLEAN ERROR MESSAGE IF THERE IS A PROBLEM WITH THE DATABASE
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void showOneVehicleSelected(int vehicleID) throws ClassNotFoundException, SQLException {
        String selectQuery = "SELECT * FROM CarType WHERE CarTypeID = ?";
        Connection conX = DataBaseConnection.getConnection();

        // PREPARE THE QUERY TO GET THE SPECIFIC VEHICLE BY ITS ID
        try (PreparedStatement PST = conX.prepareStatement(selectQuery)) {
            PST.setInt(1, vehicleID);

            try (ResultSet RS = PST.executeQuery()) {
                System.out.println("\n--- SELECTED VEHICLE ---");
                System.out.println("=========================================================");

                // PRINT THE HEADER WITH EXACT SPACING FOR THREE VARIABLES
                System.out.printf("%-5s | %-20s | %-15s %n", "ID", "VEHICLE TYPE", "PRICE");
                System.out.println("---------------------------------------------------------");

                while (RS.next()) {
                    int id = RS.getInt("CarTypeID");
                    String carType = RS.getString("CarTypeName");
                    String price = RS.getString("BaseRent");

                    System.out.printf("%-5d | %-20s | $%-14s %n", id, carType, price);
                }
                System.out.println("=========================================================\n");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public static void showOneServiceSelected(int serviceID) throws ClassNotFoundException, SQLException {
        String selectQuery = "SELECT * FROM Service WHERE ServiceID = ?";
        Connection conX = DataBaseConnection.getConnection();

        // PREPARE THE QUERY TO GET THE SPECIFIC SERVICE BY ITS ID
        try (PreparedStatement PST = conX.prepareStatement(selectQuery)) {
            PST.setInt(1, serviceID);

            try (ResultSet RS = PST.executeQuery()) {
                System.out.println("\n--- SELECTED SERVICE ---");
                System.out.println("=========================================================");
                System.out.printf("%-5s | %-20s | %-15s %n", "ID", "SERVICE NAME", "PRICE");
                System.out.println("---------------------------------------------------------");

                while (RS.next()) {
                    int id = RS.getInt("ServiceID");
                    String service = RS.getString("ServiceName");
                    String price = RS.getString("ServiceCharge");

                    System.out.printf("%-5d | %-20s | $%-14s %n", id, service, price);
                }
                System.out.println("=========================================================\n");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    /**
     * THIS QUERY GETS THE NAMES OF THE CLIENT, VEHICLE, AND SERVICE USING SUBQUERIES.
     * THIS SHOWS REAL NAMES INSTEAD OF JUST ID NUMBERS.
     */
    public static void showCaptureBooking(int clientID, int vehicleID, int serviceID, String date)
            throws ClassNotFoundException, SQLException {
        
        // ==========================================
        //        DATE FORMATTING LOGIC
        // ==========================================
        
        // CREATE A DATE FORMATTER FOR THE NEW VISUAL STYLE
        java.time.format.DateTimeFormatter visualFormat = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // CHANGE THE DATABASE STRING INTO A REAL JAVA DATE
        java.time.LocalDate parsedDate = java.time.LocalDate.parse(date);
        
        // FORMAT THE DATE INTO A NEW STRING FOR THE SCREEN
        String formattedDate = parsedDate.format(visualFormat);
        
        // ==========================================

        String selectQuery = """
                             SELECT 
                                 (SELECT CustName FROM Customer WHERE CustomerID = ?) AS Cliente,
                                 (SELECT CarTypeName FROM CarType WHERE CarTypeID = ?) AS Veiculo,
                                 (SELECT ServiceName FROM Service WHERE ServiceID = ?) AS Servico;""";

        Connection conX = DataBaseConnection.getConnection();

        try (PreparedStatement PST = conX.prepareStatement(selectQuery)) {
            PST.setInt(1, clientID);
            PST.setInt(2, vehicleID);
            PST.setInt(3, serviceID);

            try (ResultSet RS = PST.executeQuery()) {
                System.out.println("\n                          BOOKING SUMMARY");
                System.out.println("===============================================================================");
                System.out.printf("%-20s | %-20s | %-15s | %-15s %n", "CLIENT", "VEHICLE TYPE", "SERVICE", "DATE");
                System.out.println("-------------------------------------------------------------------------------");

                while (RS.next()) {
                    String name = RS.getString("Cliente");
                    String car = RS.getString("Veiculo");
                    String serviceName = RS.getString("Servico");

                    System.out.printf("%-20s | %-20s | %-15s | %-15s %n", name, car, serviceName, formattedDate);
                }
                System.out.println("===============================================================================\n");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    /**
     * THIS CALCULATES THE FINAL TOTAL PRICE OF THE BOOKING BY GETTING THE PRICES
     * FROM THE CARTYPE AND SERVICE TABLES.
     */
    public static void showPriceBooking(int vehicleID, int serviceID) throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT (SELECT BaseRent FROM CarType WHERE CarTypeID = ?) AS CarPrice, "
                + "(SELECT ServiceCharge FROM Service WHERE ServiceID = ?) AS ServicePrice";

        Connection conX = DataBaseConnection.getConnection();

        try (PreparedStatement PST = conX.prepareStatement(selectQuery)) {
            PST.setInt(1, vehicleID);
            PST.setInt(2, serviceID);

            try (ResultSet RS = PST.executeQuery()) {
                while (RS.next()) {
                    // GET THE PRICES FROM THE DATABASE AS DECIMALS
                    double vehiclePrice = RS.getDouble("CarPrice");
                    double servicePrice = RS.getDouble("ServicePrice");

                    // THE %.2F FORMAT MAKES SURE THE PRICE ALWAYS SHOWS EXACTLY TWO DECIMAL PLACES
                    System.out.printf("CAR PRICE:     $%.2f\n", vehiclePrice);
                    System.out.printf("SERVICE PRICE: $%.2f\n", servicePrice);
                    System.out.printf("TOTAL PRICE:   $%.2f\n", (vehiclePrice + servicePrice));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in showPriceBooking: " + e.getMessage());
        }
    }

    /**
     * THIS JOIN QUERY LINKS FIVE DIFFERENT TABLES TOGETHER.
     * IT SHOWS EVERYTHING ABOUT A SPECIFIC BOOKING ON ONE CLEAN LINE.
     */
    public static void showOneBookingSelected(int bookingID) throws ClassNotFoundException, SQLException {
        String query = "SELECT B.BookingID, C.CustName, T.CarTypeName, S.ServiceName, B.DateOfBooking, (T.BaseRent + S.ServiceCharge) AS Total "
                + "FROM Booking B "
                + "JOIN Customer C ON B.CustomerID = C.CustomerID "
                + "JOIN CarType T ON B.CarTypeID = T.CarTypeID "
                + "JOIN BookingService BS ON B.BookingID = BS.BookingID "
                + "JOIN Service S ON BS.ServiceID = S.ServiceID "
                + "WHERE B.BookingID = ?";

        Connection conX = DataBaseConnection.getConnection();

        try (PreparedStatement PST = conX.prepareStatement(query)) {
            PST.setInt(1, bookingID);

            try (ResultSet RS = PST.executeQuery()) {
                System.out.println("\n--- SELECTED BOOKING ---");
                System.out.printf("%-5s | %-15s | %-15s | %-15s | %-12s | %-8s%n",
                        "ID", "CLIENT", "VEHICLE", "SERVICE", "DATE", "TOTAL");
                System.out.println("---------------------------------------------------------------------------------------------");

                if (RS.next()) {
                    System.out.printf("%-5d | %-15s | %-15s | %-15s | %-12s | $%.2f%n",
                            RS.getInt("BookingID"),
                            RS.getString("CustName"),
                            RS.getString("CarTypeName"),
                            RS.getString("ServiceName"),
                            RS.getString("DateOfBooking"),
                            RS.getDouble("Total")
                    );
                }
                System.out.println("---------------------------------------------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}