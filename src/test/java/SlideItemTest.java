import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Tests for SlideItem and its subclasses.
 */
public class SlideItemTest {
    
    private Style style;
    private Graphics graphics;
    private ImageObserver observer;
    private float scale;
    
    @BeforeEach
    public void setUp() {
        // Create a mock style
        style = new Style(10, Color.BLACK, 12, 20);
        
        // Create a mock graphics context
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        
        // Create a mock image observer
        observer = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return true;
            }
        };
        
        scale = 1.0f;
    }
    
    @Test
    public void testTextItemConstructor() {
        TextItem textItem = new TextItem(1, "Test Text");
        assertEquals(1, textItem.getLevel());
        assertEquals("Test Text", textItem.getText());
    }
    
    @Test
    public void testTextItemDefaultConstructor() {
        TextItem textItem = new TextItem();
        assertEquals(0, textItem.getLevel());
        assertEquals("No Text Given", textItem.getText());
    }
    
    @Test
    public void testTextItemGetBoundingBox() {
        TextItem textItem = new TextItem(1, "Test Text");
        Rectangle boundingBox = textItem.getBoundingBox(graphics, observer, scale, style);
        assertNotNull(boundingBox);
        assertTrue(boundingBox.width > 0);
        assertTrue(boundingBox.height > 0);
    }
    
    @Test
    public void testTextItemValidate() {
        TextItem textItem = new TextItem(1, "Test Text");
        assertTrue(textItem.validate());
        
        // Create an invalid text item with negative level
        TextItem invalidTextItem = new TextItem(-1, "Test Text");
        assertFalse(invalidTextItem.validate());
    }
    
    @Test
    public void testBitmapItemConstructor() {
        // This test might fail if the image file doesn't exist
        // We'll create a mock implementation for testing
        BitmapItem bitmapItem = new BitmapItem(1, "non_existent_image.jpg");
        assertEquals(1, bitmapItem.getLevel());
        assertEquals("non_existent_image.jpg", bitmapItem.getName());
    }
    
    @Test
    public void testBitmapItemDefaultConstructor() {
        BitmapItem bitmapItem = new BitmapItem();
        assertEquals(0, bitmapItem.getLevel());
        assertNull(bitmapItem.getName());
    }
    
    @Test
    public void testBitmapItemValidate() {
        // Create a bitmap item with a non-existent image
        BitmapItem bitmapItem = new BitmapItem(1, "non_existent_image.jpg");
        assertFalse(bitmapItem.validate());
        
        // Create a valid bitmap item (no image name)
        BitmapItem validBitmapItem = new BitmapItem(1, null);
        assertTrue(validBitmapItem.validate());
    }
    
    @Test
    public void testBitmapItemGetBoundingBox() {
        // Create a bitmap item with a non-existent image
        BitmapItem bitmapItem = new BitmapItem(1, "non_existent_image.jpg");
        Rectangle boundingBox = bitmapItem.getBoundingBox(graphics, observer, scale, style);
        assertNotNull(boundingBox);
        assertEquals(100, boundingBox.width);
        assertEquals(100, boundingBox.height);
    }
} 