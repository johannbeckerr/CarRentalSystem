package validation;

import Database.DataBaseConnection;
import Utilities.ClearScreen;
import Utilities.Warnings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * THIS CLASS HANDLES INPUT VALIDATION FOR CLIENT DATA.
 * IT CHECKS THAT NAMES, DATES, AND IDS ARE CORRECT BEFORE SAVING TO THE DATABASE.
 */
public class ValidationInsertClients {

    // INSERT VALIDATIONS
    public static String captureValidName(Scanner scanner) {
        String name;
        // REQUEST THE CLIENT NAME. LOOP UNTIL A VALID NAME IS GIVEN OR THE USER CANCELS
        do {
            Warnings.promptCancel();
            System.out.print("\nCLIENT NAME: ");
            name = scanner.nextLine().trim();

            if (name.equals("0")) {
                return null;
            }
            if (name.isEmpty()) {
                Warnings.fielsMandatory();
            }
        } while (name.isEmpty());
        ClearScreen.clearScreen();
        return name;
    }

    public static String captureValidDOB(Scanner scanner) {
        String dob;
        String dataForMySQL = "";
        boolean validDate = false;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // REQUEST THE DATE OF BIRTH. CHECK IF THE FORMAT IS CORRECT (DD/MM/YYYY)
        do {
            Warnings.promptCancel();
            System.out.print("\nDATE OF BIRTH: ");
            dob = scanner.nextLine().trim();

            if (dob.equals("0")) {
                return null;
            }
            if (dob.isEmpty()) {
                Warnings.fielsMandatory();
                continue;
            }
            try {
                // PARSE THE DATE AND CONVERT IT TO THE MYSQL FORMAT (YYYY-MM-DD)
                LocalDate myDate = LocalDate.parse(dob, inputFormat);
                dataForMySQL = myDate.toString();
                validDate = true;
            } catch (DateTimeParseException e) {
                // SHOW AN ERROR IF THE DATE FORMAT IS NOT CORRECT
                Warnings.invalidDate();
            }
        } while (!validDate);
        ClearScreen.clearScreen();
        return dataForMySQL;
    }

    public static String captureValidStreet(Scanner scanner) {
        String street;
        // REQUEST THE STREET NAME. LOOP UNTIL A VALID INPUT IS PROVIDED
        do {
            Warnings.promptCancel();
            System.out.print("\nSTREET: ");
            street = scanner.nextLine().trim();

            if (street.equals("0")) {
                return null;
            }
            if (street.isEmpty()) {
                Warnings.fielsMandatory();
            }
        } while (street.isEmpty());
        ClearScreen.clearScreen();
        return street;
    }

    public static String captureValidCity(Scanner scanner) {
        String city;
        // REQUEST THE CITY NAME. LOOP UNTIL A VALID INPUT IS PROVIDED
        do {
            Warnings.promptCancel();
            System.out.print("\nCITY NAME: ");
            city = scanner.nextLine().trim();

            if (city.equals("0")) {
                return null;
            }
            if (city.isEmpty()) {
                Warnings.fielsMandatory();
            }
        } while (city.isEmpty());
        ClearScreen.clearScreen();
        return city;
    }

    // UPDATE VALIDATIONS
    public static String captureValidNewName(Scanner scanner) {
        String name;
        // REQUEST THE NEW NAME FOR THE CLIENT UPDATE
        do {
            Warnings.promptCancel();
            System.out.print("\nNEW NAME: ");
            name = scanner.nextLine().trim();

            if (name.equals("0")) {
                return null;
            }
            if (name.isEmpty()) {
                Warnings.fielsMandatory();
            }
        } while (name.isEmpty());
        ClearScreen.clearScreen();
        return name;
    }

    public static String captureValidNewCity(Scanner scanner) {
        String city;
        // REQUEST THE NEW CITY FOR THE CLIENT UPDATE
        do {
            Warnings.promptCancel();
            System.out.print("\nNEW CITY: ");
            city = scanner.nextLine().trim();

            if (city.equals("0")) {
                return null;
            }
            if (city.isEmpty()) {
                Warnings.fielsMandatory();
            }
        } while (city.isEmpty());
        ClearScreen.clearScreen();
        return city;
    }

    public static int captureValidUpdateID(Scanner scanner) throws SQLException, ClassNotFoundException {
        int id = -1;
        boolean isValidAndExists = false;

        // REQUEST THE CLIENT ID. LOOP UNTIL A VALID NUMBER IS PROVIDED AND IT EXISTS IN THE DATABASE
        do {
            Warnings.promptCancel();
            System.out.print("\nENTER THE ID OF THE CLIENT TO UPDATE: ");
            String input = scanner.nextLine().trim();

            // EXIT THE PROCESS IF THE INPUT IS ZERO
            if (input.equals("0")) {
                return 0; 
            }

            try {
                // PARSE INPUT TO INTEGER
                id = Integer.parseInt(input);
                
                // CHECK IF THE ID EXISTS IN THE DATABASE
                boolean exists = ValidationInsertClients.checkIfIdExist(id);
                
                if (exists) {
                    // EXIT THE LOOP IF THE ID IS A NUMBER AND EXISTS IN THE DATABASE
                    isValidAndExists = true; 
                } else {
                    // SHOW AN ERROR IF THE ID DOES NOT EXIST
                    Warnings.iDclientNotFound();
                }
                
            } catch (NumberFormatException e) {
                // SHOW AN ERROR IF THE INPUT IS NOT A VALID NUMBER
                Warnings.updateClientFailed();
            }
        } while (!isValidAndExists);

        ClearScreen.clearScreen();
        return id;
    }
    
    // CHECK IF THE ID EXISTS IN THE DATABASE
    public static boolean checkIfIdExist(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT 1 FROM Customer WHERE CustomerID = ?";
        Connection conX = DataBaseConnection.getConnection();
        
        // PREPARE AND EXECUTE THE QUERY TO CHECK FOR THE ID
        try (PreparedStatement PST = conX.prepareStatement(sql)) {
            PST.setInt(1, id);
            try (ResultSet rs = PST.executeQuery()) {
                // RETURN TRUE IF THE ID WAS FOUND IN THE DATABASE
                return rs.next(); 
            }
        }
    }
}