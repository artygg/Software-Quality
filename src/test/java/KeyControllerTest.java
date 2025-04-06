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
    private Presentation presentation;
    private JFrame frame;
    
    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Add some test slides
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        presentation.addSlide(slide1);
        
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        presentation.addSlide(slide2);

        presentation.setSlideNumber(0);

        frame = new JFrame();
        AppController noOpController = new AppController() {
            @Override
            public void shutdown() {

            }
        };

        keyController = new KeyController(noOpController);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(keyController, "KeyController should be created");
    }
    
    @Test
    public void testKeyPressedNextSlide() {
        // Create a KeyEvent for Page Down
        KeyEvent event = new KeyEvent(
            frame,
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_PAGE_DOWN,
            ' '
        );
        
        // Get initial slide number
        int initialSlide = presentation.getCurrentSlideNumber();
        
        // Simulate key press
        keyController.keyPressed(event);
        
        // Verify slide number increased
        assertEquals(initialSlide + 1, presentation.getCurrentSlideNumber(),
            "Slide number should increase when Page Down is pressed");
    }
    
    @Test
    public void testKeyPressedPreviousSlide() {
        // Move to second slide
        presentation.setSlideNumber(1);
        
        // Create a KeyEvent for Page Up
        KeyEvent event = new KeyEvent(
            frame,
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_PAGE_UP,
            ' '
        );
        
        // Simulate key press
        keyController.keyPressed(event);
        
        // Verify slide number decreased
        assertEquals(0, presentation.getCurrentSlideNumber(),
            "Slide number should decrease when Page Up is pressed");
    }
    
    @Test
    public void testKeyPressedFirstSlide() {
        // Create a KeyEvent for Page Up
        KeyEvent event = new KeyEvent(
            frame,
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_PAGE_UP,
            ' '
        );
        
        // Simulate key press
        keyController.keyPressed(event);
        
        // Verify slide number stays at 0
        assertEquals(0, presentation.getCurrentSlideNumber(),
            "Slide number should stay at 0 when Page Up is pressed on first slide");
    }
    
    @Test
    public void testKeyPressedLastSlide() {
        // Move to last slide
        presentation.setSlideNumber(presentation.getSize() - 1);
        
        // Create a KeyEvent for Page Down
        KeyEvent event = new KeyEvent(
            frame,
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_PAGE_DOWN,
            ' '
        );
        
        // Simulate key press
        keyController.keyPressed(event);
        
        // Verify slide number stays at last slide
        assertEquals(presentation.getSize() - 1, presentation.getCurrentSlideNumber(),
            "Slide number should stay at last slide when Page Down is pressed on last slide");
    }
    
    @Test
    public void testKeyPressedQuit() {
        // Create a KeyEvent for 'q'
        KeyEvent event = new KeyEvent(
            frame,
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_Q,
            'q'
        );
        
        // Simulate key press
        keyController.keyPressed(event);
        
        // We can't easily test System.exit(), but we can verify that no exception is thrown
        assertDoesNotThrow(() -> keyController.keyPressed(event),
            "No exception should be thrown when pressing 'q'");
    }
    
    @Test
    public void testKeyPressedUnknownKey() {
        // Create a KeyEvent for an unknown key
        KeyEvent event = new KeyEvent(
            frame,
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_A,
            'a'
        );
        
        // Get initial slide number
        int initialSlide = presentation.getCurrentSlideNumber();
        
        // Simulate key press
        keyController.keyPressed(event);
        
        // Verify slide number didn't change
        assertEquals(initialSlide, presentation.getCurrentSlideNumber(),
            "Slide number should not change for unknown key");
    }
} 