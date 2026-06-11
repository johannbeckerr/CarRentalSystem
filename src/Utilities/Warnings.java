package Utilities;

import static Utilities.Blinking.BLINK;
import static Utilities.Blinking.RESET;
import static Utilities.Colors.BG_CYAN;
import static Utilities.Colors.BG_RED;
import static Utilities.Colors.CYAN;
import static Utilities.Colors.GREEN;
import static Utilities.Colors.PURPLE;
import static Utilities.Colors.RED;
import static Utilities.Colors.YELLOW;
import java.util.Scanner;

/**
 * THIS CLASS CONTAINS ALL THE WARNING AND NOTIFICATION MESSAGES FOR THE APPLICATION.
 * IT USES COLORS AND BLINKING TEXT TO MAKE IMPORTANT MESSAGES STAND OUT.
 * MANY OF THESE METHODS REQUIRE THE USER TO PRESS ENTER TO CONTINUE.
 */
public class Warnings {

    public static void invalidOption() {
        System.out.println(BLINK + PURPLE + "\nINVALID OPTION! PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void goBack() {
        System.out.println(BLINK + CYAN + "\nENTER TO GO BACK" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void fielsMandatory() {
        System.out.println(BLINK + PURPLE + "\nALL FIELDS ARE MANDATORY. TRY AGAIN!" + RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void invalidDate() {
        System.out.println(BLINK + BG_RED + "\nINVALID DATE. USE THE FORMAT -> DD/MM/YYYY!" + RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void insertClientSuccess() {
        System.out.println(BLINK + GREEN +"\nCUSTOMER REGISTERED SUCCESSFULLY"+ RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void insertClientFailed() {
        System.out.println(BLINK + BG_RED +"\nFAILED TO REGISTER THE CUSTOMER. NO CHANGES MADE"+ RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void updateClientFailed() {
        System.out.println(BLINK + BG_RED +"\nINVALID INPUT! PLEASE ENTER A VALID NUMERIC ID"+ RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void updateClientSuccess() {
        System.out.println(BLINK + GREEN +"\nCUSTOMER UPDATED"+ RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void iDclientNotFound() {
        System.out.println(BLINK + PURPLE +"\nNO CUSTOMER FOUND WITH THE PROVIDED ID"+ RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void checkIfIdExist(int idAltera) {
        System.out.println(BLINK + PURPLE +"\nID " + idAltera + " not found."+ RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void cancelRegistration() throws InterruptedException {
        System.out.println(BLINK + YELLOW +"\nRETURNING TO MENU..."+ RESET);
        // PAUSE THE THREAD FOR 2 SECONDS BEFORE CLEARING THE SCREEN
        Thread.sleep(2000);
        ClearScreen.clearScreen();
    }

    public static void promptCancel(){
        System.out.println(CYAN + "\nTYPE 0 TO CANCEL" + RESET);
    }

    public static void exiting(){
        System.out.println(BLINK + YELLOW + "\nExiting..." + RESET);
    }

    public static void saveBooking(){
        System.out.println(YELLOW + "\nENTER TO SAVE BOOKING" + RESET);
    }

    public static void bookingConfirmed(){
        System.out.println(BLINK + GREEN + "\nBOOKING CONFIRMED" + RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
    }

    public static void bookingCancel(){
        System.out.println(BLINK + YELLOW + "\nPRESS '1' TO CONFIRM THE CANCELLATION (or any other key to abort)" + RESET);
    }

    public static void bookingCanceled(){
        System.out.println(BLINK + GREEN + "\nBOOKING CANCELED" + RESET);
    }

    public static void vehicleNotFoundID(){
        System.out.println(BLINK + PURPLE + "\nVEHICLE ID DOES NOT EXIST. PLEASE TRY AGAIN" + RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void ServiceNotFoundID(){
        System.out.println(BLINK + PURPLE + "\nSERVICE ID DOES NOT EXIST. PLEASE TRY AGAIN" + RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }

    public static void BookingNotFoundID(){
        System.out.println(BLINK + PURPLE + "\nBOOKING ID DOES NOT EXIST. PLEASE TRY AGAIN" + RESET);
        System.out.println(CYAN + "PRESS ENTER" + RESET);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        ClearScreen.clearScreen();
    }
}