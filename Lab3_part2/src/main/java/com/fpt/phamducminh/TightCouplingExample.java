package com.fpt.phamducminh;

import java.util.logging.Level;
import java.util.logging.Logger;

class Printer {
    private static final Logger logger = Logger.getLogger(Printer.class.getName());

    void print(String message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, "Message: {0}", message);
        }
    }
}

public class TightCouplingExample {
    private final Printer printer = new Printer(); // still tightly coupled

    public void generateReport(String reportName) {
        String message = "Generating report: " + reportName;
        printer.print(message);
    }

    public static void main(String[] args) {
        TightCouplingExample example = new TightCouplingExample();
        example.generateReport("Sales_Q2_2025");
    }
}
