package Utilities;

/**
 * THIS CLASS ADDS A BLINKING EFFECT TO TEXT IN THE TERMINAL.
 * IT USES SPECIAL ANSI ESCAPE CODES TO CHANGE HOW THE TEXT LOOKS.
 */
public class Blinking {

    // THIS IS THE SPECIAL CODE TO START THE BLINKING EFFECT
    public static final String BLINK = "\u001B[5m";
    
    // THIS IS THE CODE TO STOP THE FORMATTING AND RETURN TO NORMAL TEXT
    public static final String RESET = "\u001B[0m";

    /**
     * THIS METHOD TAKES A STRING AND PUTS THE BLINKING CODES AROUND IT.
     * IT RETURNS THE NEW FORMATTED STRING SO IT CAN BE PRINTED.
     */
    public static String wrap(String text) {
        return BLINK + text + RESET;
    }

    /**
     * THIS METHOD IS NOT FINISHED YET.
     * IT THROWS AN ERROR IF SOMEONE TRIES TO CALL IT.
     */
    public static void blinking() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}