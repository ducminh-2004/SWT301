package com.fpt.phamducminh;
import java.util.logging.Logger;
import java.util.logging.Level;

interface LoginHandler {
    boolean login(String username, String password);
}

class SimpleLoginHandler implements LoginHandler {
    private static final Logger logger = Logger.getLogger(SimpleLoginHandler.class.getName());

    @Override
    public boolean login(String username, String password) {
        boolean success = "admin".equals(username) && "admin123".equals(password);
        logger.log(Level.INFO, "Login attempt for user \"{0}\" - Success: {1}", new Object[]{username, success});
        return success;
    }
}

public class InterfaceNamingInconsistencyExample {
    private static final Logger logger = Logger.getLogger(InterfaceNamingInconsistencyExample.class.getName());

    public static void main(String[] args) {
        LoginHandler handler = new SimpleLoginHandler();
        boolean result = handler.login("admin", "admin123");

        logger.log(Level.INFO, "Login result: {0}", result);
    }
}
