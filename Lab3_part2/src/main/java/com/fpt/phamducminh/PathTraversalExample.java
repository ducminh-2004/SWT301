package com.fpt.phamducminh;

import java.io.*;
import java.util.logging.Logger;

 class PathTraversalSafeExample {
    private static final Logger logger = Logger.getLogger(PathTraversalSafeExample.class.getName());

    public static void main(String[] args) throws IOException {
        String userInput = "../secret.txt";

        File baseDir = new File("uploads");
        File file = new File(baseDir, userInput).getCanonicalFile();

        if (!file.getPath().startsWith(baseDir.getCanonicalPath())) {
            logger.severe("Access denied: Invalid file path.");
            return;
        }

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                logger.info("Reading file: " + file.getPath());
                String line;
                while ((line = reader.readLine()) != null) {
                    logger.info(line);
                }
            } catch (IOException e) {
                logger.severe("Error reading file: " + e.getMessage());
            }
        } else {
            logger.warning("File not found.");
        }
    }
}
