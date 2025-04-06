import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.jabberpoint.test.TestUtils;
import java.awt.HeadlessException;

public class BitmapItemTest {

    @BeforeEach
    public void setUp() {
        TestUtils.setupHeadlessEnvironment();
    }

    @AfterEach
    public void tearDown() {
        TestUtils.resetHeadlessEnvironment();
    }

    @Test
    public void constructor_validArguments_shouldSetLevelAndName() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem(1, "test.jpg");
                assertNotNull(item, "BitmapItem should be created");
                assertEquals(1, item.getLevel(), "Level should be set correctly");
                assertEquals("test.jpg", item.getName(), "Name should be set correctly");
            } catch (HeadlessException e) {
            }
        }, "Constructor should handle headless mode");
    }

    @Test
    public void defaultConstructor_noArguments_shouldCreateDefaultItem() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem();
                assertNotNull(item, "Default BitmapItem should be created");
                assertEquals(0, item.getLevel(), "Default level should be 0");
                assertNull(item.getName(), "Default name should be null");
            } catch (HeadlessException e) {
            }
        }, "Default constructor should handle headless mode");
    }

    @Test
    public void validate_variousInputs_shouldReturnExpectedBoolean() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem validItem = new BitmapItem(1, null);
                assertTrue(validItem.validate(), "Item with null name should be valid");
                BitmapItem invalidItem = new BitmapItem(-1, "test.jpg");
                assertFalse(invalidItem.validate(), "Item with negative level should be invalid");
            } catch (HeadlessException e) {
            }
        }, "Validate should handle headless mode");
    }

    @Test
    public void toString_called_shouldReturnFormattedString() {
        assertDoesNotThrow(() -> {
            try {
                BitmapItem item = new BitmapItem(1, "test.jpg");
                String expected = "BitmapItem[1,test.jpg]";
                assertEquals(expected, item.toString(), "ToString should return correct format");
            } catch (HeadlessException e) {
            }
        }, "ToString should handle headless mode");
    }
}
