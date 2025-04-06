package org.jabberpoint.test;

import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

/**
 * Utility class for test setup.
 */
public class TestUtils {
    
    private static boolean isHeadless = false;
    
    /**
     * Setup the headless environment for testing.
     */
    public static void setupHeadlessEnvironment() {
        try {
            // Set the java.awt.headless property
            System.setProperty("java.awt.headless", "true");
            
            // Verify we're in headless mode
            if (!GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadlessInstance()) {
                throw new HeadlessException("Failed to set headless mode");
            }
            
            isHeadless = true;
            
        } catch (Exception e) {
            System.err.println("Failed to setup headless environment: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Reset the headless environment.
     */
    public static void resetHeadlessEnvironment() {
        if (isHeadless) {
            System.setProperty("java.awt.headless", "false");
            isHeadless = false;
        }
    }
    
    /**
     * Check if we're in headless mode.
     */
    public static boolean isHeadless() {
        return isHeadless;
    }
} 