import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * Headless version of ErrorDisplay for testing.
 * This class replaces the GUI-based ErrorDisplay in test environments.
 */
public class HeadlessErrorDisplay {
    
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream testOut = new PrintStream(outputStream);
    
    /**
     * Initialize the headless error display.
     */
    public static void init() {
        System.setOut(testOut);
        System.setErr(testOut);
    }
    
    /**
     * Reset the output streams.
     */
    public static void reset() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        outputStream.reset();
    }
    
    /**
     * Get the output as a string.
     */
    public static String getOutput() {
        return outputStream.toString();
    }
    
    /**
     * Display an error message.
     */
    public static void showError(String message, String title) {
        System.err.println("ERROR: " + (title != null ? title : "Error") + " - " + message);
    }
    
    /**
     * Display an error message with default title.
     */
    public static void showError(String message) {
        showError(message, "Error");
    }
    
    /**
     * Display a warning message.
     */
    public static void showWarning(String message, String title) {
        System.err.println("WARNING: " + (title != null ? title : "Warning") + " - " + message);
    }
    
    /**
     * Display a warning message with default title.
     */
    public static void showWarning(String message) {
        showWarning(message, "Warning");
    }
    
    /**
     * Display an information message.
     */
    public static void showInfo(String message, String title) {
        System.out.println("INFO: " + (title != null ? title : "Information") + " - " + message);
    }
    
    /**
     * Display an information message with default title.
     */
    public static void showInfo(String message) {
        showInfo(message, "Information");
    }
} 