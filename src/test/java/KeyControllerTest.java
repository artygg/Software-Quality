import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * Tests for the KeyController class.
 */
public class KeyControllerTest {
    
    private KeyController keyController;
    private JFrame frame;
    
    @BeforeEach
    public void setUp() {
        frame = new JFrame();
        keyController = new KeyController();
    }
    
    @Test
    public void testKeyPressed() {
        // Create a key event
        KeyEvent keyEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, 
            System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D');
        
        // Verify the keyPressed method doesn't throw exceptions
        assertDoesNotThrow(() -> keyController.keyPressed(keyEvent),
            "keyPressed should not throw exceptions");
    }
    
    @Test
    public void testKeyReleased() {
        // Create a key event
        KeyEvent keyEvent = new KeyEvent(frame, KeyEvent.KEY_RELEASED,
            System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D');
        
        // Verify the keyReleased method doesn't throw exceptions
        assertDoesNotThrow(() -> keyController.keyReleased(keyEvent),
            "keyReleased should not throw exceptions");
    }
    
    @Test
    public void testKeyTyped() {
        // Create a key event
        KeyEvent keyEvent = new KeyEvent(frame, KeyEvent.KEY_TYPED,
            System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, 'd');
        
        // Verify the keyTyped method doesn't throw exceptions
        assertDoesNotThrow(() -> keyController.keyTyped(keyEvent),
            "keyTyped should not throw exceptions");
    }
} 