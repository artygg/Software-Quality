import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SlideItem and its subclasses.
 */
public class SlideItemTest {
    
    @Test
    public void testTextItemConstructor() {
        TextItem textItem = new TextItem(1, "Test Text");
        assertEquals(1, textItem.getLevel(), "Level should be set correctly");
        assertEquals("Test Text", textItem.getText(), "Text should be set correctly");
    }
    
    @Test
    public void testTextItemDefaultConstructor() {
        TextItem textItem = new TextItem();
        assertEquals(0, textItem.getLevel(), "Default level should be 0");
        assertEquals("No Text Given", textItem.getText(), "Default text should be 'No Text Given'");
    }
    
    @Test
    public void testTextItemValidate() {
        TextItem validItem = new TextItem(1, "Test Text");
        assertTrue(validItem.validate(), "Valid text item should pass validation");
        
        TextItem invalidItem = new TextItem(-1, "Test Text");
        assertFalse(invalidItem.validate(), "Item with negative level should fail validation");
    }
    
    @Test
    public void testBitmapItemConstructor() {
        BitmapItem bitmapItem = new BitmapItem(1, "test.jpg");
        assertEquals(1, bitmapItem.getLevel(), "Level should be set correctly");
        assertEquals("test.jpg", bitmapItem.getName(), "Name should be set correctly");
    }
    
    @Test
    public void testBitmapItemDefaultConstructor() {
        BitmapItem bitmapItem = new BitmapItem();
        assertEquals(0, bitmapItem.getLevel(), "Default level should be 0");
        assertNull(bitmapItem.getName(), "Default name should be null");
    }
    
    @Test
    public void testBitmapItemValidate() {
        BitmapItem validItem = new BitmapItem(1, null);
        assertTrue(validItem.validate(), "Item with null name should be valid");
        
        BitmapItem invalidItem = new BitmapItem(-1, "test.jpg");
        assertFalse(invalidItem.validate(), "Item with negative level should be invalid");
    }
} 