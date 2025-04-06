import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Tests for the BitmapItem class.
 */
public class BitmapItemTest {
    
    private BitmapItem item;
    private Graphics graphics;
    private ImageObserver observer;
    private Rectangle area;
    private Style style;
    private float scale = 1.0f;
    
    @BeforeEach
    public void setUp() {
        item = new BitmapItem(1, "test.jpg");
        graphics = mock(Graphics.class);
        observer = mock(ImageObserver.class);
        area = new Rectangle(0, 0, 800, 600);
        style = Style.getStyle(1);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(item, "BitmapItem should be created");
        assertEquals(1, item.getLevel(), "Level should be set correctly");
        assertEquals("test.jpg", item.getName(), "Name should be set correctly");
    }
    
    @Test
    public void testDefaultConstructor() {
        BitmapItem defaultItem = new BitmapItem();
        assertNotNull(defaultItem, "Default BitmapItem should be created");
        assertEquals(0, defaultItem.getLevel(), "Default level should be 0");
        assertNull(defaultItem.getName(), "Default name should be null");
    }
    
    @Test
    public void testGetBoundingBox() {
        Rectangle boundingBox = item.getBoundingBox(graphics, observer, scale, style);
        assertNotNull(boundingBox, "Bounding box should not be null");
        assertTrue(boundingBox.width > 0, "Bounding box width should be positive");
        assertTrue(boundingBox.height > 0, "Bounding box height should be positive");
    }
    
    @Test
    public void testDraw() {
        // Test that draw doesn't throw an exception
        assertDoesNotThrow(() -> item.draw(0, 0, scale, graphics, style, observer),
            "Draw should not throw an exception");
    }
    
    @Test
    public void testToString() {
        String expected = "BitmapItem[1,test.jpg]";
        assertEquals(expected, item.toString(), "ToString should return correct format");
    }
} 