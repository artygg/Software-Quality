package org.jabberpoint.test;

import java.lang.reflect.Field;

/**
 * Utility class for test setup.
 */
public class TestUtils {
    
    /**
     * Replace the ErrorDisplay class with HeadlessErrorDisplay for testing.
     */
    public static void setupHeadlessEnvironment() {
        try {
            // Use reflection to replace the ErrorDisplay class with HeadlessErrorDisplay
            Class<?> errorDisplayClass = Class.forName("ErrorDisplay");
            Field[] fields = errorDisplayClass.getDeclaredFields();
            
            // Initialize the headless error display
            HeadlessErrorDisplay.init();
            
            // Replace the static methods
            replaceStaticMethod(errorDisplayClass, "showError", HeadlessErrorDisplay.class.getMethod("showError", String.class, String.class));
            replaceStaticMethod(errorDisplayClass, "showError", HeadlessErrorDisplay.class.getMethod("showError", String.class));
            replaceStaticMethod(errorDisplayClass, "showWarning", HeadlessErrorDisplay.class.getMethod("showWarning", String.class, String.class));
            replaceStaticMethod(errorDisplayClass, "showWarning", HeadlessErrorDisplay.class.getMethod("showWarning", String.class));
            replaceStaticMethod(errorDisplayClass, "showInfo", HeadlessErrorDisplay.class.getMethod("showInfo", String.class, String.class));
            replaceStaticMethod(errorDisplayClass, "showInfo", HeadlessErrorDisplay.class.getMethod("showInfo", String.class));
            
        } catch (Exception e) {
            System.err.println("Failed to setup headless environment: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Reset the headless environment.
     */
    public static void resetHeadlessEnvironment() {
        HeadlessErrorDisplay.reset();
    }
    
    /**
     * Replace a static method with another method.
     */
    private static void replaceStaticMethod(Class<?> targetClass, String methodName, java.lang.reflect.Method replacementMethod) {
        // This is a placeholder for the actual implementation
        // In a real implementation, we would use bytecode manipulation or a mocking framework
        System.out.println("Replacing " + methodName + " with " + replacementMethod.getName());
    }
} 