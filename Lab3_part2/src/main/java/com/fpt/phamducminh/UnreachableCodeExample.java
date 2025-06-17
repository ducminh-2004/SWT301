package com.fpt.phamducminh;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger logger = Logger.getLogger(UnreachableCodeExample.class.getName());

    private static final int DEFAULT_NUMBER = 42; // Constant replaces method

    public static void main(String[] args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, "Returned number: {0}", DEFAULT_NUMBER);
        }
    }
}

