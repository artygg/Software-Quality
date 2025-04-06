import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlideItemFactoryTest {

    private static class CustomItemFactory extends SlideItemFactory {
        @Override
        public SlideItem createSlideItem(int level, String content) {
            return new TextItem(level, "Custom: " + content);
        }
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void createSlideItem_validTextType_shouldReturnTextItem() {
        SlideItem item = SlideItemFactory.create("text", 1, "Test Text");
        assertTrue(item instanceof TextItem, "Created item should be a TextItem");
        assertEquals(1, item.getLevel(), "Item level should be 1");
        assertEquals("Test Text", ((TextItem) item).getText(), "Item text should be 'Test Text'");
    }

    @Test
    public void createSlideItem_unknownType_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SlideItemFactory.create("unknown", 1, "Test");
        }, "Should throw an IllegalArgumentException for unknown type");
    }

    @Test
    public void registerCreator_customCreatorRegistered_shouldReturnCustomTextItem() {
        SlideItemFactory.registerCreator("custom", new CustomItemFactory());
        SlideItem item = SlideItemFactory.create("custom", 2, "Test");
        assertTrue(item instanceof TextItem, "Created item should be a TextItem");
        assertEquals(2, item.getLevel(), "Item level should be 2");
        assertEquals("Custom: Test", ((TextItem) item).getText(), "Item text should be 'Custom: Test'");
    }
}
