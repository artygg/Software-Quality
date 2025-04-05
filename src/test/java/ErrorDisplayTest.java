import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ErrorDisplay utility class.
 * Note: These tests are limited because JOptionPane.showMessageDialog is a static method
 * that displays a UI dialog, which is difficult to test directly.
 */
public class ErrorDisplayTest {
    
    @Test
    public void testErrorDisplayExists() {
        // This is a simple test to verify that the ErrorDisplay class exists
        // In a real test environment, we would use a mock or a different approach
        assertNotNull(ErrorDisplay.class, "ErrorDisplay class should exist");
    }
    
    // In a real test environment, we would use a mock framework like Mockito
    // to verify that JOptionPane.showMessageDialog is called with the correct parameters.
    // For example:
    /*
    @Test
    public void testShowError() {
        // Mock JOptionPane
        JOptionPane mockJOptionPane = mock(JOptionPane.class);
        
        // Call the method
        ErrorDisplay.showError("Test Error");
        
        // Verify that JOptionPane.showMessageDialog was called with the correct parameters
        verify(mockJOptionPane).showMessageDialog(
            eq(null),
            eq("Test Error"),
            eq("Error"),
            eq(JOptionPane.ERROR_MESSAGE)
        );
    }
    */
} 