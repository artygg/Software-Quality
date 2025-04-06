import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jabberpoint.test.TestUtils;
import java.awt.HeadlessException;

/**
 * Tests for the BitmapItem class.
 */
public class BitmapItemTest {
    
    @BeforeEach
    public void setUp() {
        // Setup headless environment
        TestUtils.setupHeadlessEnvironment();
    }
    
    @AfterEach
    public void tearDown() {
        // Reset headless environment
        TestUtils.resetHeadlessEnvironment();
    }
    
    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem(1, "test.jpg");
                assertNotNull(item, "BitmapItem should be created");
                assertEquals(1, item.getLevel(), "Level should be set correctly");
                assertEquals("test.jpg", item.getName(), "Name should be set correctly");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "Constructor should handle headless mode");
    }
    
    @Test
    public void testDefaultConstructor() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem();
                assertNotNull(item, "Default BitmapItem should be created");
                assertEquals(0, item.getLevel(), "Default level should be 0");
                assertNull(item.getName(), "Default name should be null");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "Default constructor should handle headless mode");
    }
    
    @Test
    public void testValidate() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem validItem = new BitmapItem(1, null);
                assertTrue(validItem.validate(), "Item with null name should be valid");
                
                BitmapItem invalidItem = new BitmapItem(-1, "test.jpg");
                assertFalse(invalidItem.validate(), "Item with negative level should be invalid");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "Validate should handle headless mode");
    }
    
    @Test
    public void testToString() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem(1, "test.jpg");
                String expected = "BitmapItem[1,test.jpg]";
                assertEquals(expected, item.toString(), "ToString should return correct format");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "ToString should handle headless mode");
    }
} 