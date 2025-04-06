import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the SlideItemFactory class.
 */
public class SlideItemFactoryTest {
    
    private static class CustomItemFactory extends SlideItemFactory {
        @Override
        public SlideItem createSlideItem(int level, String content) {
            return new TextItem(level, "Custom: " + content);
        }
    }
    
    @BeforeEach
    public void setUp() {
        // No need to clear creators as they are registered in static block
    }
    
    @Test
    public void testCreateTextItem() {
        // Create a text item
        SlideItem item = SlideItemFactory.create("text", 1, "Test Text");
        
        // Verify that it's a TextItem
        assertTrue(item instanceof TextItem, "Created item should be a TextItem");
        
        // Verify its properties
        assertEquals(1, item.getLevel(), "Item level should be 1");
        assertEquals("Test Text", ((TextItem) item).getText(), "Item text should be 'Test Text'");
    }
    
    @Test
    public void testCreateBitmapItem() {
        // Create a bitmap item
        SlideItem item = SlideItemFactory.create("image", 1, "test.jpg");
        
        // Verify that it's a BitmapItem
        assertTrue(item instanceof BitmapItem, "Created item should be a BitmapItem");
        
        // Verify its properties
        assertEquals(1, item.getLevel(), "Item level should be 1");
        assertEquals("test.jpg", ((BitmapItem) item).getName(), "Item name should be 'test.jpg'");
    }

    @Test
    public void testCreateUnknownItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            SlideItemFactory.create("unknown", 1, "Test");
        }, "Should throw an IllegalArgumentException for unknown type");
    }
    
    @Test
    public void testRegisterCreator() {
        // Register a custom creator
        SlideItemFactory.registerCreator("custom", new CustomItemFactory());
        
        // Create an item using the custom creator
        SlideItem item = SlideItemFactory.create("custom", 2, "Test");
        
        // Verify that it's a TextItem with the custom text
        assertTrue(item instanceof TextItem, "Created item should be a TextItem");
        assertEquals(2, item.getLevel(), "Item level should be 2");
        assertEquals("Custom: Test", ((TextItem) item).getText(), "Item text should be 'Custom: Test'");
    }
} 