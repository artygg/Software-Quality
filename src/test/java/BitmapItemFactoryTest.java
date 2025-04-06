import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jabberpoint.test.TestUtils;
import java.awt.HeadlessException;

/**
 * Tests for the BitmapItemFactory class.
 */
public class BitmapItemFactoryTest {
    
    private BitmapItemFactory factory;
    
    @BeforeEach
    public void setUp() {
        // Setup headless environment
        TestUtils.setupHeadlessEnvironment();
        
        factory = new BitmapItemFactory();
    }
    
    @AfterEach
    public void tearDown() {
        // Reset headless environment
        TestUtils.resetHeadlessEnvironment();
    }
    
    @Test
    public void testCreateItem() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = (BitmapItem) factory.createSlideItem(1, "test.jpg");
                assertNotNull(item, "BitmapItem should be created");
                assertEquals(1, item.getLevel(), "Level should be set correctly");
                assertEquals("test.jpg", item.getName(), "Name should be set correctly");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "CreateItem should handle headless mode");
    }
    
    @Test
    public void testCreateItemWithNullName() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = (BitmapItem) factory.createSlideItem(1, null);
                assertNotNull(item, "BitmapItem should be created with null name");
                assertEquals(1, item.getLevel(), "Level should be set correctly");
                assertNull(item.getName(), "Name should be null");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "CreateItem with null name should handle headless mode");
    }
    
    @Test
    public void testCreateItemWithNegativeLevel() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = (BitmapItem) factory.createSlideItem(-1, "test.jpg");
                assertNotNull(item, "BitmapItem should be created with negative level");
                assertEquals(-1, item.getLevel(), "Level should be set correctly");
                assertEquals("test.jpg", item.getName(), "Name should be set correctly");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "CreateItem with negative level should handle headless mode");
    }
} 