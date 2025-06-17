package com.fpt.phamducminh;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger logger = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    // Private constructor to prevent instantiation (optional best practice)
    private CatchGenericExceptionExample() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            logger.info("Please enter a string:");
            String s = scanner.nextLine();

            if (s != null && !s.trim().isEmpty()) {
                logger.log(Level.INFO, "String length: {0}", s.length());
            } else {
                logger.warning("The input string is null or empty. Cannot compute length.");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An unexpected error occurred: {0}", e.getMessage());
        }

        logger.info("Program completed successfully.");
    }
}
