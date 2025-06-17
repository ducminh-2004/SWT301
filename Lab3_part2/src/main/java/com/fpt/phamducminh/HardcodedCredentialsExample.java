package com.fpt.phamducminh;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class HardcodedCredentialsExample {

    private static final Logger logger = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    public static void main(String[] args) {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            logger.severe("Could not load configuration file: " + e.getMessage());
            return;
        }

        String username = props.getProperty("username");
        String password = props.getProperty("password");

        if (username == null || password == null) {
            logger.severe("Username or password not found in config.properties");
            return;
        }

        if (authenticate(username, password)) {
            logger.info("Access granted");
        } else {
            logger.warning("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Logic xác thực giả lập, ví dụ cho demo
        return "admin".equals(user) && "123456".equals(pass);
    }
}
