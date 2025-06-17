package com.fpt.phamducminh;
import java.util.logging.Logger;
 class AppConstants {
    public static final int MAX_USERS = 100;

    // Private constructor to prevent instantiation
    private AppConstants() {
        throw new UnsupportedOperationException("Utility class");
    }
}

public class InterfaceFieldModificationExample {
    private static final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        logger.info("Max users allowed: " + AppConstants.MAX_USERS);
    }
}
