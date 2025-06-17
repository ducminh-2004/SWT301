package com.fpt.phamducminh;

import java.util.logging.Logger;

// Define the interface
interface Drawable {
    void draw();
}

// Implementing class
class Circle implements Drawable {
    private static final Logger logger = Logger.getLogger(Circle.class.getName());

    @Override
    public void draw() {
        logger.info("Drawing a circle");
    }
}

// Main class to test
public class UnimplementedInterfaceExample {
    public static void main(String[] args) {
        Drawable shape = new Circle();  // Use interface type
        shape.draw();                   // Call draw() method
    }
}
