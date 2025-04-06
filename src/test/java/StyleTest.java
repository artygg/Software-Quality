import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Font;

public class StyleTest {

    private Style style;

    @BeforeEach
    public void setUp() {
        Style.createStyles();
        style = Style.getStyle(0);
    }

    @Test
    public void constructor_validArguments_shouldCreateNonNullStyle() {
        assertNotNull(style, "Style should not be null");
    }

    @Test
    public void getFont_validScale_shouldReturnNonNullFont() {
        Font f = style.getFont(1.0f);
        assertNotNull(f, "Font should not be null");
    }

    @Test
    public void createStyles_called_shouldCreateFiveStyles() {
        Style.createStyles();
        for (int i = 0; i < 5; i++) {
            assertNotNull(Style.getStyle(i), "Style at index " + i + " should not be null");
        }
    }

    @Test
    public void getStyle_outOfRangeLevel_shouldReturnLastStyle() {
        Style lastStyle = Style.getStyle(4);
        Style outOfRange = Style.getStyle(100);
        assertEquals(lastStyle, outOfRange, "Out of range level should return the last style");
    }

    @Test
    public void toString_called_shouldContainIndentColorFontSizeAndLeading() {
        String result = style.toString();
        assertTrue(result.contains(String.valueOf(style.indent)), "toString should contain indent value");
        assertTrue(result.contains(String.valueOf(style.fontSize)), "toString should contain font size");
        assertTrue(result.contains(String.valueOf(style.leading)), "toString should contain leading value");
        assertTrue(result.contains(String.valueOf(style.color)), "toString should contain color value");
    }

    @Test
    public void getFont_validScale_shouldReturnFontWithScaledSize() {
        float scale = 1.5f;
        Font derivedFont = style.getFont(scale);
        float expectedSize = style.fontSize * scale;
        assertEquals(expectedSize, derivedFont.getSize2D(), 0.01, "Derived font size should be scaled correctly");
    }
}
