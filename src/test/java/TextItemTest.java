import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the TextItem class.
 */
public class TextItemTest {
    
    @BeforeAll
    public static void initStyles() {
        Style.createStyles();
    }
    
    @Test
    public void testConstructor() {
        TextItem item = new TextItem(1, "Test Text");
        assertNotNull(item, "TextItem should be created");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("Test Text", item.getText(), "Text should be set correctly");
    }
    
    @Test
    public void testDefaultConstructor() {
        TextItem item = new TextItem();
        assertNotNull(item, "Default TextItem should be created");
        assertEquals(0, item.getLevel(), "Default level should be 0");
        assertEquals("No Text Given", item.getText(), "Default text should be 'No Text Given'");
    }
    
    @Test
    public void testValidate() {
        TextItem validItem = new TextItem(1, "Test Text");
        assertTrue(validItem.validate(), "Valid text item should pass validation");
        
        TextItem invalidItem = new TextItem(-1, "Test Text");
        assertFalse(invalidItem.validate(), "Item with negative level should fail validation");
    }
    
    @Test
    public void testToString() {
        TextItem item = new TextItem(1, "Test Text");
        String expected = "TextItem[1,Test Text]";
        assertEquals(expected, item.toString(), "ToString should return correct format");
    }
} 