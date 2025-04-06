import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jabberpoint.test.TestUtils;
import javax.swing.JFrame;

/**
 * Tests for the AboutBox class.
 */
public class AboutBoxTest {
    
    private JFrame frame;
    
    @BeforeEach
    public void setUp() {
        // Setup headless environment
        TestUtils.setupHeadlessEnvironment();
        
        // Create a frame for testing
        frame = new JFrame();
    }
    
    @Test
    public void testShow() {
        // Create an AboutBox instance
        AboutBox aboutBox = new AboutBox();
        
        // Verify the show method doesn't throw exceptions
        assertDoesNotThrow(() -> aboutBox.show(frame), 
            "AboutBox.show() should not throw exceptions in headless mode");
    }
} 