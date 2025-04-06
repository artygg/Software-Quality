import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the BitmapItem class.
 */
public class BitmapItemTest {
    
    @Test
    public void testConstructor() {
        BitmapItem item = new BitmapItem(1, "test.jpg");
        assertNotNull(item, "BitmapItem should be created");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("test.jpg", item.getName(), "Name should be set correctly");
    }
    
    @Test
    public void testDefaultConstructor() {
        BitmapItem item = new BitmapItem();
        assertNotNull(item, "Default BitmapItem should be created");
        assertEquals(0, item.getLevel(), "Default level should be 0");
        assertNull(item.getName(), "Default name should be null");
    }
    
    @Test
    public void testValidate() {
        BitmapItem validItem = new BitmapItem(1, null);
        assertTrue(validItem.validate(), "Item with null name should be valid");
        
        BitmapItem invalidItem = new BitmapItem(-1, "test.jpg");
        assertFalse(invalidItem.validate(), "Item with negative level should be invalid");
    }
    
    @Test
    public void testToString() {
        BitmapItem item = new BitmapItem(1, "test.jpg");
        String expected = "BitmapItem[1,test.jpg]";
        assertEquals(expected, item.toString(), "ToString should return correct format");
    }
} 