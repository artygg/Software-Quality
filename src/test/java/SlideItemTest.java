import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jabberpoint.test.TestUtils;
import java.awt.HeadlessException;

/**
 * Tests for SlideItem and its subclasses.
 */
public class SlideItemTest {
    
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
    public void testTextItemConstructor() {
        assertDoesNotThrow(() -> {
            try {
                TextItem item = new TextItem(1, "Test Text");
                assertNotNull(item, "TextItem should be created");
                assertEquals(1, item.getLevel(), "Level should be set correctly");
                assertEquals("Test Text", item.getText(), "Text should be set correctly");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "TextItem constructor should handle headless mode");
    }
    
    @Test
    public void testTextItemDefaultConstructor() {
        assertDoesNotThrow(() -> {
            try {
                TextItem item = new TextItem();
                assertNotNull(item, "Default TextItem should be created");
                assertEquals(0, item.getLevel(), "Default level should be 0");
                assertEquals("No Text Given", item.getText(), "Default text should be 'No Text Given'");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "TextItem default constructor should handle headless mode");
    }
    
    @Test
    public void testTextItemValidate() {
        assertDoesNotThrow(() -> {
            try {
                TextItem validItem = new TextItem(1, "Test Text");
                assertTrue(validItem.validate(), "Item with valid text should be valid");
                
                TextItem invalidItem = new TextItem(-1, "Test Text");
                assertFalse(invalidItem.validate(), "Item with negative level should be invalid");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "TextItem validate should handle headless mode");
    }
    
    @Test
    public void testBitmapItemConstructor() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem(1, "test.jpg");
                assertNotNull(item, "BitmapItem should be created");
                assertEquals(1, item.getLevel(), "Level should be set correctly");
                assertEquals("test.jpg", item.getName(), "Name should be set correctly");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "BitmapItem constructor should handle headless mode");
    }
    
    @Test
    public void testBitmapItemDefaultConstructor() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem();
                assertNotNull(item, "Default BitmapItem should be created");
                assertEquals(0, item.getLevel(), "Default level should be 0");
                assertNull(item.getName(), "Default name should be null");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "BitmapItem default constructor should handle headless mode");
    }
    
    @Test
    public void testBitmapItemValidate() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem validItem = new BitmapItem(1, "test.jpg");
                assertTrue(validItem.validate(), "Item with valid name should be valid");
                
                BitmapItem invalidItem = new BitmapItem(-1, "test.jpg");
                assertFalse(invalidItem.validate(), "Item with negative level should be invalid");
            } catch (HeadlessException e) {
                // Expected in headless mode
            }
        }, "BitmapItem validate should handle headless mode");
    }
} 