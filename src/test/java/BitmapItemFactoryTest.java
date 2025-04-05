import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the BitmapItemFactory class.
 */
public class BitmapItemFactoryTest {
    
    private BitmapItemFactory factory;
    
    @BeforeEach
    public void setUp() {
        factory = new BitmapItemFactory();
    }
    
    @Test
    public void testCreateItem() {
        BitmapItem item = (BitmapItem) factory.createSlideItem(1, "test.jpg");
        
        assertNotNull(item, "Created item should not be null");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("test.jpg", item.getName(), "Name should be set correctly");
    }
    
    @Test
    public void testCreateItemWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createSlideItem(1, null);
        }, "Should throw IllegalArgumentException when name is null");
    }
    
    @Test
    public void testCreateItemWithEmptyName() {
        BitmapItem item = (BitmapItem) factory.createSlideItem(1, "");
        
        assertNotNull(item, "Created item should not be null");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("", item.getName(), "Name should be empty string");
    }
} 