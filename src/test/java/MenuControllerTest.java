import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;

/**
 * Tests for the MenuController class.
 */
public class MenuControllerTest {
    
    private MenuController menuController;
    private Frame parent;
    
    @BeforeEach
    public void setUp() {
        parent = mock(Frame.class);
        menuController = new MenuController(parent);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(menuController, "MenuController should be created");
    }
    
    @Test
    public void testFileMenu() {
        Menu fileMenu = menuController.getMenu(0);
        assertNotNull(fileMenu, "File menu should exist");
        assertEquals("File", fileMenu.getLabel(), "File menu should have correct label");
        
        // Verify menu items
        int itemCount = fileMenu.getItemCount();
        assertTrue(itemCount >= 4, "File menu should have at least 4 items");
        assertEquals("Open", fileMenu.getItem(0).getLabel(), "First item should be Open");
        assertEquals("New", fileMenu.getItem(1).getLabel(), "Second item should be New");
        assertEquals("Save", fileMenu.getItem(2).getLabel(), "Third item should be Save");
    }
    
    @Test
    public void testViewMenu() {
        Menu viewMenu = menuController.getMenu(1);
        assertNotNull(viewMenu, "View menu should exist");
        assertEquals("View", viewMenu.getLabel(), "View menu should have correct label");
        
        // Verify menu items
        int itemCount = viewMenu.getItemCount();
        assertTrue(itemCount >= 3, "View menu should have at least 3 items");
        assertEquals("Next", viewMenu.getItem(0).getLabel(), "First item should be Next");
        assertEquals("Prev", viewMenu.getItem(1).getLabel(), "Second item should be Prev");
        assertEquals("Go to", viewMenu.getItem(2).getLabel(), "Third item should be Go to");
    }
    
    @Test
    public void testHelpMenu() {
        Menu helpMenu = menuController.getMenu(2);
        assertNotNull(helpMenu, "Help menu should exist");
        assertEquals("Help", helpMenu.getLabel(), "Help menu should have correct label");
        
        // Verify menu items
        int itemCount = helpMenu.getItemCount();
        assertEquals(1, itemCount, "Help menu should have 1 item");
        assertEquals("About", helpMenu.getItem(0).getLabel(), "Item should be About");
    }
    
    @Test
    public void testMenuActionListeners() {
        // Get the File menu
        Menu fileMenu = menuController.getMenu(0);
        MenuItem openItem = fileMenu.getItem(0);
        
        // Create a mock ActionEvent
        ActionEvent event = new ActionEvent(openItem, ActionEvent.ACTION_PERFORMED, "Open");
        
        // Verify that the action listener is attached
        assertDoesNotThrow(() -> openItem.getActionListeners()[0].actionPerformed(event),
            "Action listener should handle the event");
    }
} 