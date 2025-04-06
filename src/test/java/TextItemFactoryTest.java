import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextItemFactoryTest {

    private TextItemFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new TextItemFactory();
    }

    @Test
    public void createSlideItem_validText_shouldReturnTextItem() {
        TextItem item = (TextItem) factory.createSlideItem(1, "Test Text");
        assertNotNull(item, "Created item should not be null");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("Test Text", item.getText(), "Text should be set correctly");
    }

    @Test
    public void createSlideItem_nullText_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createSlideItem(1, null);
        }, "Should throw IllegalArgumentException when text is null");
    }

    @Test
    public void createSlideItem_emptyText_shouldReturnTextItemWithEmptyText() {
        TextItem item = (TextItem) factory.createSlideItem(1, "");
        assertNotNull(item, "Created item should not be null");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("", item.getText(), "Text should be empty string");
    }
}
