













        /*
        DEAR PROFESSOR KEV,

        MY PROJECT WORKS IN THE NETBEANS TERMINAL, BUT I RECOMMEND RUNNING IT IN A NORMAL TERMINAL 
        (LIKE THE WINDOWS COMMAND PROMPT, POWERSHELL, OR MACOS TERMINAL).

        I ADDED SPECIAL FORMATS AND INTERACTIVE FEATURES FOR A BETTER USER EXPERIENCE. 
        IDE TERMINALS OFTEN CANNOT SHOW SOME VISUAL EFFECTS (LIKE CLEARING THE SCREEN). 
        SO, USING A NORMAL TERMINAL WILL LET YOU SEE THE PROJECT EXACTLY AS I PLANNED.

        THANK YOU!
        */













package main;

import Database.DataBaseConnection;
import java.sql.SQLException;
import menus.MainMenu;

public class Main {
    
    /**
     * THE MAIN METHOD STARTS THE APPLICATION.
     * IT TRIES TO CONNECT TO THE DATABASE AND LOAD THE USER MENU.
     */
    public static void main(String[] args) {
        
        /*
         * A TRY-CATCH BLOCK STOPS THE APP FROM CRASHING
         * IF THERE IS A DATABASE CONNECTION PROBLEM (FOR EXAMPLE, A WRONG 
         * PASSWORD, A MISSING DRIVER, OR THE MYSQL SERVER IS OFFLINE).
         */
        try {
            // 1. TRY TO CONNECT TO THE DATABASE SECURELY
            DataBaseConnection.getConnection();
            
            // 2. IF THE CONNECTION IS SUCCESSFUL, LOAD THE MAIN MENU
            MainMenu.mainMenu();
            
        } catch (SQLException | ClassNotFoundException e) {
            /*
             * THIS CATCH BLOCK HANDLES DATABASE AND DRIVER ERRORS.
             * WE DO NOT SHOW A BIG RED ERROR LOG TO THE USER. INSTEAD, WE PRINT A CLEAN
             * AND PROFESSIONAL MESSAGE TO EXPLAIN THE PROBLEM.
             */
            System.out.println("Critical Error: Could not connect to the database.");
            System.out.println("Technical Details: " + e.getMessage());
            
        } catch (Exception e) {
            /*
             * THIS IS A GENERAL CATCH BLOCK.
             * IT CATCHES ANY OTHER UNEXPECTED ERRORS THAT HAPPEN WHILE THE PROGRAM IS RUNNING,
             * WHICH ARE NOT ABOUT THE DATABASE.
             */
            System.out.println("An unexpected system error occurred: " + e.getMessage());
        }
    }
}