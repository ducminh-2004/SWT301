package com.fpt.phamducminh;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViolationOfEncapsulationExample {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ViolationOfEncapsulationExample.class.getName());

        User user = new User();
        user.setName("Alice");
        user.setAge(30);

        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, "Displaying user info:");
        }

        user.display();
    }
}

class User {
    private String name;
    private int age;

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public void display() {
        Logger logger = Logger.getLogger(User.class.getName());
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, "Name: {0}, Age: {1}", new Object[]{name, age});
        }
    }
}
