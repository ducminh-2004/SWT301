package com.fpt.phamducminh;

import java.util.logging.Level;
import java.util.logging.Logger;

class SQLInjectionExample {

    private static final Logger logger = Logger.getLogger(SQLInjectionExample.class.getName());

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";

        String vulnerableQuery = "SELECT * FROM users WHERE username = '" + userInput + "'";
        if (logger.isLoggable(Level.WARNING)) {
            logger.warning("Vulnerable SQL: " + vulnerableQuery);
        }

        String safeQuery = "SELECT * FROM users WHERE username = ?";
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Safe SQL with PreparedStatement: " + safeQuery);
        }
    }
}
