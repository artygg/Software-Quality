import javax.swing.JOptionPane;


public class ErrorDisplay {
    

    public static void showError(String message, String title) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title != null ? title : "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    

    public static void showError(String message) {
        showError(message, "Error");
    }
    

    public static void showWarning(String message, String title) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title != null ? title : "Warning", 
            JOptionPane.WARNING_MESSAGE
        );
    }
    

    public static void showWarning(String message) {
        showWarning(message, "Warning");
    }
    

    public static void showInfo(String message, String title) {
        JOptionPane.showMessageDialog(
            null, 
            message, 
            title != null ? title : "Information", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    

    public static void showInfo(String message) {
        showInfo(message, "Information");
    }
} 