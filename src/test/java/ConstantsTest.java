import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Constants class.
 */
public class ConstantsTest {
    
    @Test
    public void testConstantsExist() {
        // Verify that the Constants class exists
        try {
            Class<?> constantsClass = Class.forName("Constants");
            assertNotNull(constantsClass, "Constants class should exist");
        } catch (ClassNotFoundException e) {
            fail("Constants class not found: " + e.getMessage());
        }
    }
    
    @Test
    public void testConstantValues() {
        // Verify that the constants have the expected values
        try {
            Class<?> constantsClass = Class.forName("Constants");
            
            // Check WIDTH constant
            int width = (int) constantsClass.getField("WIDTH").getInt(null);
            assertTrue(width > 0, "WIDTH should be positive");
            
            // Check HEIGHT constant
            int height = (int) constantsClass.getField("HEIGHT").getInt(null);
            assertTrue(height > 0, "HEIGHT should be positive");
            
            // Check XPOS constant
            int xpos = (int) constantsClass.getField("XPOS").getInt(null);
            assertTrue(xpos >= 0, "XPOS should be non-negative");
            
            // Check YPOS constant
            int ypos = (int) constantsClass.getField("YPOS").getInt(null);
            assertTrue(ypos >= 0, "YPOS should be non-negative");
            
            // Check the relationship between WIDTH and XPOS
            assertTrue(width > xpos, "WIDTH should be greater than XPOS");
            
            // Check the relationship between HEIGHT and YPOS
            assertTrue(height > ypos, "HEIGHT should be greater than YPOS");
        } catch (Exception e) {
            fail("Error accessing constants: " + e.getMessage());
        }
    }
} 