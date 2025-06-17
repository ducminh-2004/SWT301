package com.fpt.phamducminh;
import java.util.Scanner;
import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger logger = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            logger.info("Please enter a string:");
            String text = scanner.nextLine();

            if (text != null && !text.isEmpty()) {
                logger.info("Text is not empty");
            } else {
                logger.warning("Text is null or empty");
            }
        }
    }
}
