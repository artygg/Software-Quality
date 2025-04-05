import javax.swing.JOptionPane;

/**
 * Utility class for displaying errors to users in a consistent way.
 * This follows the Single Responsibility Principle by centralizing error display logic.
 */
public class ErrorDisplay {
    
    /**
     * Display an error message to the user.
     * 
     * @param message The error message to display
     * @param title The title of the error dialog (optional, defaults to "Error")
     */
    public static void showError(String message, String title) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title != null ? title : "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Display an error message to the user with the default title.
     * 
     * @param message The error message to display
     */
    public static void showError(String message) {
        showError(message, "Error");
    }
    
    /**
     * Display a warning message to the user.
     * 
     * @param message The warning message to display
     * @param title The title of the warning dialog (optional, defaults to "Warning")
     */
    public static void showWarning(String message, String title) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title != null ? title : "Warning", 
            JOptionPane.WARNING_MESSAGE
        );
    }
    
    /**
     * Display a warning message to the user with the default title.
     * 
     * @param message The warning message to display
     */
    public static void showWarning(String message) {
        showWarning(message, "Warning");
    }
    
    /**
     * Display an information message to the user.
     * 
     * @param message The information message to display
     * @param title The title of the information dialog (optional, defaults to "Information")
     */
    public static void showInfo(String message, String title) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title != null ? title : "Information", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Display an information message to the user with the default title.
     * 
     * @param message The information message to display
     */
    public static void showInfo(String message) {
        showInfo(message, "Information");
    }
} 