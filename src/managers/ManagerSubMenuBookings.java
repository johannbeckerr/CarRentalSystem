package managers;

import java.sql.SQLException;

/**
 * THIS CLASS ACTS AS A CONTROLLER FOR THE BOOKING SUB-MENU.
 * IT CALLS THE SPECIFIC METHODS IN THE MANAGERBOOKING CLASS BASED ON THE CHOSEN ACTION.
 */
public class ManagerSubMenuBookings {
    
    // THIS METHOD HANDLES THE ACTION TO CREATE A NEW BOOKING
    public static void case1() throws ClassNotFoundException, SQLException, InterruptedException {
        ManagerBooking.allCapture();
    }
    
    // THIS METHOD HANDLES THE ACTION TO VIEW ALL EXISTING BOOKINGS
    public static void case2() throws ClassNotFoundException, SQLException {
        ManagerBooking.viewAllBookings();
    }
    
    // THIS METHOD HANDLES THE ACTION TO DELETE AN EXISTING BOOKING
    public static void case3() throws ClassNotFoundException, SQLException, InterruptedException {
        ManagerBooking.deleteBooking();
    }
}