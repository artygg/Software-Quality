import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

/**
 * Tests for the Style class.
 */
public class StyleTest {
    
    private Style style;
    
    @BeforeEach
    public void setUp() {
        style = Style.getStyle(0);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(style, "Style should not be null");
    }
    
    @Test
    public void testGetFont() {
        assertNotNull(style.getFont(1.0f), "Font should not be null");
    }
} 