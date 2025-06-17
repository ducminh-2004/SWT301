package com.fpt.phamducminh;
import java.util.logging.Level;
import java.util.logging.Logger;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    private static final Logger logger = Logger.getLogger(Square.class.getName());

    public void draw() {
        logger.log(Level.INFO, "Drawing square");
    }

    public void resize() {
        logger.log(Level.INFO, "Resizing square");
    }
}

public class IncompleteInterfaceImplementationExample {
    public static void main(String[] args) {
        Shape shape = new Square();
        shape.draw();
        shape.resize();
    }
}
