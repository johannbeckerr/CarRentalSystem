package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {

    /**
     * THIS METHOD CHANGES A DATE STRING FROM ISO FORMAT (YYYY-MM-DD) TO DISPLAY FORMAT (DD/MM/YYYY).
     * IT RETURNS "N/A" IF THE INPUT IS NULL. IT RETURNS THE ORIGINAL STRING IF THE TEXT CANNOT BE READ.
     *
     * @return THE FORMATTED DATE STRING IN DD/MM/YYYY. "N/A" IF NULL. THE ORIGINAL STRING IF IT CANNOT BE READ.
     */
    public static String dateFormatter(String DOB){

        // RETURN "N/A" IF NO DATE IS GIVEN
        if(DOB == null) return "N/A";

        try{
            // READ THE INPUT STRING AS AN ISO DATE (YYYY-MM-DD)
            LocalDate myDate = LocalDate.parse(DOB);

            // SET THE NEW OUTPUT FORMAT
            DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // RETURN THE DATE FORMATTED AS DD/MM/YYYY
            return myDate.format(myDateFormat);

        }
        catch(DateTimeParseException e){

            // IF THE STRING CANNOT BE READ, RETURN IT AS IT IS
            return DOB;
        }
    }

    /**
     * THIS METHOD CHANGES A DATE STRING FROM DISPLAY FORMAT (DD/MM/YYYY) BACK TO ISO FORMAT (YYYY-MM-DD).
     * IT RETURNS NULL IF THE INPUT CANNOT BE READ.
     *
     * THE DATE STRING IN ISO FORMAT (YYYY-MM-DD), OR NULL IF IT CANNOT BE READ
     */
    public static String dateFormatterBooking(String dateBooking){

        // SET THE EXPECTED INPUT FORMAT FOR BOOKING DATES
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            // READ THE BOOKING DATE USING THE DD/MM/YYYY FORMAT
            LocalDate myDate = LocalDate.parse(dateBooking, myDateFormat);

            // RETURN THE DATE AS A STRING IN THE DEFAULT ISO FORMAT (YYYY-MM-DD)
            return myDate.toString();

        }
        catch(DateTimeParseException e){

            // IF THE READING FAILS, RETURN NULL TO SHOW AN INVALID DATE
            return null;
        }
    }
}