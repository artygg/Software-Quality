import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class HeadlessErrorDisplay {

    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final PrintStream testOut = new PrintStream(outputStream);

    public static void init() {
        System.setOut(testOut);
        System.setErr(testOut);
    }

    public static void reset() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        outputStream.reset();
    }

    public static String getOutput() {
        return outputStream.toString();
    }

    public static void showError(String message, String title) {
        System.err.println("ERROR: " + (title != null ? title : "Error") + " - " + message);
    }

    public static void showError(String message) {
        showError(message, "Error");
    }

    public static void showWarning(String message, String title) {
        System.err.println("WARNING: " + (title != null ? title : "Warning") + " - " + message);
    }

    public static void showWarning(String message) {
        showWarning(message, "Warning");
    }

    public static void showInfo(String message, String title) {
        System.out.println("INFO: " + (title != null ? title : "Information") + " - " + message);
    }

    public static void showInfo(String message) {
        showInfo(message, "Information");
    }
}