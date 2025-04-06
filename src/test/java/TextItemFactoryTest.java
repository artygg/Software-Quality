import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TextItemFactory class.
 */
public class TextItemFactoryTest {
    
    private TextItemFactory factory;
    
    @BeforeEach
    public void setUp() {
        factory = new TextItemFactory();
    }
    
    @Test
    public void testCreateItem() {
        TextItem item = (TextItem) factory.createSlideItem(1, "Test Text");
        
        assertNotNull(item, "Created item should not be null");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("Test Text", item.getText(), "Text should be set correctly");
    }
    
    @Test
    public void testCreateItemWithNullText() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createSlideItem(1, null);
        }, "Should throw IllegalArgumentException when text is null");
    }
    
    @Test
    public void testCreateItemWithEmptyText() {
        TextItem item = (TextItem) factory.createSlideItem(1, "");
        
        assertNotNull(item, "Created item should not be null");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("", item.getText(), "Text should be empty string");
    }
} 