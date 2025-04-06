import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextItemTest {

    @BeforeAll
    public static void initStyles_shouldInitializeStyles() {
        Style.createStyles();
    }

    @Test
    public void constructor_validArguments_shouldCreateTextItem() {
        TextItem item = new TextItem(1, "Test Text");
        assertNotNull(item, "TextItem should be created");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("Test Text", item.getText(), "Text should be set correctly");
    }

    @Test
    public void defaultConstructor_noArguments_shouldCreateDefaultTextItem() {
        TextItem item = new TextItem();
        assertNotNull(item, "Default TextItem should be created");
        assertEquals(0, item.getLevel(), "Default level should be 0");
        assertEquals("No Text Given", item.getText(), "Default text should be 'No Text Given'");
    }

    @Test
    public void validate_validAndInvalidInputs_shouldReturnCorrectBoolean() {
        TextItem validItem = new TextItem(1, "Test Text");
        assertTrue(validItem.validate(), "Valid text item should pass validation");
        TextItem invalidItem = new TextItem(-1, "Test Text");
        assertFalse(invalidItem.validate(), "Item with negative level should fail validation");
    }

    @Test
    public void toString_called_shouldReturnFormattedString() {
        TextItem item = new TextItem(1, "Test Text");
        String expected = "TextItem[1,Test Text]";
        assertEquals(expected, item.toString(), "ToString should return correct format");
    }
}
