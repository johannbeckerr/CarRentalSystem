package menus;

import Utilities.ClearScreen;
import Utilities.Warnings;
import java.sql.SQLException;
import java.util.Scanner;
import managers.ManagerSubMenuBookings;
import managers.ManagerSubMenuClients;
import managers.ManagerSubMenuFinancial;

/**
 * THIS CLASS IS THE NAVIGATION CONTROLLER OF THE APPLICATION.
 * IT SEPARATES THE UI INTERACTION LAYER FROM THE DATABASE LOGIC.
 * EACH METHOD ACTS AS A ROUTING HUB, DIRECTING USER SELECTION 
 * TO THE CORRECT MANAGER CLASS TO PERFORM SQL OPERATIONS.
 */
public class SubMenus {

    /**
     * CONTROL THE NAVIGATION FOR THE CUSTOMER MENU.
     * USE A LOOP TO KEEP THE MENU ACTIVE UNTIL THE USER SELECTS ZERO.
     */
    public static void subMenusClients() throws ClassNotFoundException, SQLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        
        // CONTROL FLAG FOR THE MENU LOOP; SET TO FALSE WHEN THE USER CHOOSES TO EXIT
        boolean decision = true;

        while (decision) {
            System.out.println("            CLIENTS            \n");
            System.out.println("1 - REGISTER NEW CUSTOMER");
            System.out.println("2 - LIST ALL CUSTOMERS");
            System.out.println("3 - UPDATE CUSTOMER DETAILS");
            System.out.println("\n0 - GO BACK\n");
            System.out.print("-> ");

            // CAPTURE USER INPUT AND PERFORM BASIC PARSING
            int subMenuOption = -1;
            String input = scanner.nextLine();
            
            // TRY-CATCH BLOCK TO HANDLE NON-NUMERIC INPUT WITHOUT CRASHING THE APP
            try {
                subMenuOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // IF INPUT IS NOT A NUMBER, OPTION REMAINS NEGATIVE ONE, TRIGGERING THE DEFAULT CASE
            }

            // SWITCH STATEMENT ROUTES USER CHOICES TO THE CORRECT MANAGER CLASS
            switch (subMenuOption) {
                case 1:
                    ClearScreen.clearScreen(); // CLEAR CONSOLE FOR A CLEAN UI EXPERIENCE
                    ManagerSubMenuClients.case1();
                    break;
                case 2:
                    ClearScreen.clearScreen();
                    ManagerSubMenuClients.case2();
                    break;
                case 3:
                    ClearScreen.clearScreen();
                    ManagerSubMenuClients.case3();
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    decision = false; // EXIT THE LOOP AND RETURN THE USER TO THE MAIN MENU
                    break;
                default:
                    // FRIENDLY MESSAGE FOR INVALID SELECTION
                    Warnings.invalidOption();
            }
        }
    }

    /**
     * CONTROL THE NAVIGATION FOR THE BOOKING MANAGEMENT MENU.
     * DIRECT LOGIC TO THE BOOKING MANAGER CLASS FOR CREATING, VIEWING, OR DELETING RECORDS.
     */
    public static void subMenusBookings() throws ClassNotFoundException, SQLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean decision = true;

        while (decision) {
            System.out.println("            BOOKINGS            \n");
            System.out.println("1 - CREATE BOOKINGS");
            System.out.println("2 - VIEW ALL BOOKINGS");
            System.out.println("3 - CANCEL BOOKINGS");
            System.out.println("\n0 - GO BACK\n");
            System.out.print("-> ");

            int subMenuOption = -1;
            String input = scanner.nextLine();
            
            try {
                subMenuOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // IGNORE EXCEPTION TO HANDLE NON-INTEGER INPUT VIA THE DEFAULT SWITCH CASE
            }

            switch (subMenuOption) {
                case 1:
                    ClearScreen.clearScreen();
                    ManagerSubMenuBookings.case1();
                    break;
                case 2:
                    ClearScreen.clearScreen();
                    ManagerSubMenuBookings.case2();
                    break;
                case 3:
                    ClearScreen.clearScreen();
                    ManagerSubMenuBookings.case3();
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    decision = false;
                    break;
                default:
                    Warnings.invalidOption();
            }
        }
    }
    
    /**
     * CONTROL THE NAVIGATION FOR THE FINANCIAL SUMMARY MENU.
     * DISPLAY THE FINAL REPORT AND THEN RETURN TO THE MAIN MENU.
     */
    public static void subMenusFinancial() throws ClassNotFoundException, SQLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean decision = true;

        while (decision) {
            System.out.println("            FINANCIAL            \n");
            System.out.println("1 - TOTAL REVENUE");
            System.out.println("0 - GO BACK");
            System.out.print("-> ");

            int subMenuOption = -1;
            String input = scanner.nextLine();
            
            try {
                subMenuOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // IGNORE EXCEPTION
            }

            switch (subMenuOption) {
                case 1:
                    ClearScreen.clearScreen();
                    ManagerSubMenuFinancial.case1();
                    
                    // EXPLICIT PAUSE TO ALLOW THE USER TO READ THE REPORT BEFORE SCREEN CLEARS
                    System.out.println("\n\n\n\n\n\n\n\n\n\nPRESS [ENTER] TO GO BACK");
                    scanner.nextLine(); 
                    
                    // FINISH THE MENU ACTION AND EXIT THE LOOP
                    decision = false;
                    ClearScreen.clearScreen();
                    break;
                case 0:
                    ClearScreen.clearScreen();
                    decision = false;
                    break;
                default:
                    Warnings.invalidOption();
            }
        }
    }
}