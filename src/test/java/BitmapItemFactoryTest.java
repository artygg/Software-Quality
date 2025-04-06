import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.jabberpoint.test.TestUtils;
import java.awt.HeadlessException;

public class BitmapItemFactoryTest {

    private BitmapItemFactory factory;

    @BeforeEach
    public void setUp() {
        TestUtils.setupHeadlessEnvironment();
        factory = new BitmapItemFactory();
    }

    @AfterEach
    public void tearDown() {
        TestUtils.resetHeadlessEnvironment();
    }

    @Test
    public void createSlideItem_validArguments_shouldReturnBitmapItem() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = (BitmapItem) factory.createSlideItem(1, "test.jpg");
                assertNotNull(item, "BitmapItem should be created");
                assertEquals(1, item.getLevel(), "Level should be set correctly");
                assertEquals("test.jpg", item.getName(), "Name should be set correctly");
            } catch (HeadlessException e) {
            }
        }, "CreateItem should handle headless mode");
    }

    @Test
    public void createSlideItem_nullName_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                factory.createSlideItem(1, null);
            } catch (HeadlessException e) {
            }
        }, "Should throw IllegalArgumentException when name is null");
    }

    @Test
    public void createSlideItem_negativeLevel_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                factory.createSlideItem(-1, "test.jpg");
            } catch (HeadlessException e) {
            }
        }, "Should throw IllegalArgumentException when level is negative");
    }
}
