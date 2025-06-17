package com.fpt.phamducminh;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger logger = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        int[] arr = new int[5];

        // Initialize array to avoid "never written to" warning
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i + 1) * 10;
        }

        int indexToAccess = 2; // ✅ Valid index: avoids ArrayIndexOutOfBoundsException

        try {
            int value = arr[indexToAccess];
            logger.log(Level.INFO, "Value at index {0} is {1}", new Object[]{indexToAccess, value});
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Invalid index {0}: {1}", new Object[]{indexToAccess, e.getMessage()});
        }
    }
}
