package com.fpt.phamducminh;

import java.util.logging.Logger;

class Animal {
    private static final Logger logger = Logger.getLogger(Animal.class.getName());

    void speak() {
        logger.info("Animal speaks");
    }
}

class Dog extends Animal {
    private static final Logger logger = Logger.getLogger(Dog.class.getName());

    @Override
    void speak() {
        logger.info("Dog barks");
    }
}

public class MissingOverrideAnnotationExample {
    public static void main(String[] args) {
        Animal a = new Dog();  // using polymorphism
        a.speak();             // triggers Dog's speak() method
    }
}
