import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Frame;
import java.awt.MenuItem;

/**
 * Tests for the MenuController class.
 */
public class MenuControllerTest {
    
    private MenuController menuController;
    private Frame frame;
    
    @BeforeEach
    public void setUp() {
        frame = new Frame();
        menuController = new MenuController(frame);
    }
    
    @Test
    public void testMkMenuItem() {
        // Create a menu item
        MenuItem menuItem = menuController.mkMenuItem("Test");
        
        // Verify the menu item is not null
        assertNotNull(menuItem, "Menu item should not be null");
        
        // Verify the menu item label
        assertEquals("Test", menuItem.getLabel(), "Menu item label should match");
    }
    
    @Test
    public void testConstructorWithCustomOpenCommand() {
        // Create a menu controller with a custom open command
        OpenCommand customOpenCommand = new OpenCommand(frame);
        MenuController customController = new MenuController(frame, customOpenCommand);
        
        // Verify the controller is created
        assertNotNull(customController, "Menu controller should not be null");
    }
} 