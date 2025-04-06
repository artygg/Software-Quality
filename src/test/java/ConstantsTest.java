import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConstantsTest {

    @Test
    public void constantsExistence_constantsClass_shouldNotBeNull() {
        try {
            Class<?> constantsClass = Class.forName("Constants");
            assertNotNull(constantsClass, "Constants class should exist");
        } catch (ClassNotFoundException e) {
            fail("Constants class not found: " + e.getMessage());
        }
    }

    @Test
    public void constantValues_constants_shouldMeetExpectedConstraints() {
        try {
            Class<?> constantsClass = Class.forName("Constants");
            int width = (int) constantsClass.getField("DEFAULT_SLIDE_WIDTH").getInt(null);
            assertTrue(width > 0, "WIDTH should be positive");
            int height = (int) constantsClass.getField("DEFAULT_SLIDE_HEIGHT").getInt(null);
            assertTrue(height > 0, "HEIGHT should be positive");
            int xpos = (int) constantsClass.getField("TITLE_COORD_X").getInt(null);
            assertTrue(xpos >= 0, "XPOS should be non-negative");
            int ypos = (int) constantsClass.getField("TITLE_COORD_Y").getInt(null);
            assertTrue(ypos >= 0, "YPOS should be non-negative");
            assertTrue(width > xpos, "WIDTH should be greater than XPOS");
            assertTrue(height > ypos, "HEIGHT should be greater than YPOS");
        } catch (Exception e) {
            fail("Error accessing constants: " + e.getMessage());
        }
    }
}
