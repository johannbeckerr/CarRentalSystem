package menus;

import Utilities.ClearScreen;
import Utilities.Warnings;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * THIS CLASS MANAGES THE MAIN MENU OF THE SYSTEM.
 * IT DISPLAYS OPTIONS TO THE USER AND CALLS THE CORRECT SUBMENUS.
 */
public class MainMenu {

    public static void mainMenu() throws ClassNotFoundException, SQLException, InterruptedException {
        ClearScreen.clearScreen();
        Scanner scanner = new Scanner(System.in);

        boolean decision = true;

        while (decision) {
            System.out.println("\n        BOOKING.COMpiler_OS | v1.17.4201-LTWS             \n\n");

            // DISPLAY THE MENU OPTIONS
            System.out.println("1 - CLIENTS");
            System.out.println("2 - BOOKINGS");
            System.out.println("3 - FINACIAL REPORT");
            System.out.println("\n4 - EXIT\n");
            System.out.print("-> ");
            
            // DECLARE THE VARIABLE BEFORE THE TRY-CATCH BLOCK
            int mainMenuOption = 0;

            // READ THE INPUT AS A STRING FIRST
            String input = scanner.nextLine();

            // ATTEMPT TO CONVERT THE STRING TO AN INTEGER
            try {
                mainMenuOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // THE CATCH BLOCK RUNS IF THE INPUT IS NOT A NUMBER.
                // THE OPTION REMAINS AT DEFAULT VALUE IF CONVERSION FAILS.
            }

            // PROCESS THE MENU OPTION USING A SWITCH STATEMENT
            switch (mainMenuOption) {
                case 1:
                    ClearScreen.clearScreen();
                    SubMenus.subMenusClients();
                    break;
                case 2:
                    ClearScreen.clearScreen();
                    SubMenus.subMenusBookings();
                    break;
                case 3:
                    ClearScreen.clearScreen();
                    SubMenus.subMenusFinancial();
                    break;
                case 4:
                    Warnings.exiting();
                    Thread.sleep(2000);
                    ClearScreen.clearScreen();
                    decision = false;
                    break;

                default:
                    // SHOW AN ERROR IF THE OPTION IS NOT VALID
                    Warnings.invalidOption();
                    break;
            }
        }
    }
}