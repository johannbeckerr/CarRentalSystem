package managers;

import SQLs.InsertClient;
import SQLs.SelectClients;
import SQLs.UpdateClients;
import Utilities.ClearScreen;
import Utilities.Warnings;
import java.sql.SQLException;
import java.util.Scanner;
import validation.ValidationInsertClients;

/**
 * THIS CLASS MANAGES THE SUB-MENU ACTIONS FOR CLIENTS.
 * IT HANDLES THE REGISTRATION OF NEW CLIENTS, VIEWING THE CLIENT LIST,
 * AND UPDATING EXISTING CLIENT INFORMATION.
 */
public class ManagerSubMenuClients {

    public static void case1() throws SQLException, ClassNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // CAPTURE CLIENT NAME
        String client = ValidationInsertClients.captureValidName(scanner);
        
        // IF CAPTUREVALIDNAME RETURNS NULL, THE INPUT IS INVALID. CANCEL THE REGISTRATION PROCESS.
        if (client == null) {
            Warnings.cancelRegistration();
            return;
        }

        // CAPTURE DATE OF BIRTH
        String dataForMySQL = ValidationInsertClients.captureValidDOB(scanner);
        if (dataForMySQL == null) {
            Warnings.cancelRegistration();
            return;
        }

        // CAPTURE STREET
        String street = ValidationInsertClients.captureValidStreet(scanner);
        if (street == null) {
            Warnings.cancelRegistration();
            return;
        }

        // CAPTURE CITY
        String city = ValidationInsertClients.captureValidCity(scanner);
        if (city == null) {
            Warnings.cancelRegistration();
            return;
        }

        // SAVE DATA TO THE DATABASE IF ALL INPUTS ARE VALID
        InsertClient.insertClients(client, dataForMySQL, street, city);

        Warnings.insertClientSuccess();
    }

    public static void case2() throws ClassNotFoundException, SQLException, InterruptedException {

        // DISPLAY THE LIST OF CLIENTS
        SelectClients.selectClients();
        Warnings.goBack();
    }

    public static void case3() throws SQLException, ClassNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // CAPTURE AND VERIFY THE ID. THE LOOP HANDLES THE DATABASE CHECK AUTOMATICALLY
        int updateID = ValidationInsertClients.captureValidUpdateID(scanner);
        if (updateID == 0) {
            Warnings.cancelRegistration();
            return;
        }

        // CAPTURE NEW NAME
        String newName = ValidationInsertClients.captureValidNewName(scanner);
        if (newName == null) {
            Warnings.cancelRegistration();
            return;
        }

        // CAPTURE NEW CITY
        String newCity = ValidationInsertClients.captureValidNewCity(scanner);
        if (newCity == null) {
            Warnings.cancelRegistration();
            return;
        }

        // EXECUTE THE UPDATE PROCESS
        UpdateClients.updateClients(updateID, newName, newCity);
        Warnings.updateClientSuccess();
    }
}