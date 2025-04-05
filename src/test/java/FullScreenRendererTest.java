import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Tests for the FullScreenRenderer class.
 */
public class FullScreenRendererTest {
    
    private FullScreenRenderer renderer;
    private Slide slide;
    private Graphics graphics;
    private Rectangle area;
    private ImageObserver observer;
    
    @BeforeEach
    public void setUp() {
        renderer = new FullScreenRenderer();
        slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        
        graphics = mock(Graphics.class);
        area = new Rectangle(0, 0, 800, 600);
        observer = mock(ImageObserver.class);
    }
    
    @Test
    public void testRender() {
        // Test that render doesn't throw an exception
        assertDoesNotThrow(() -> renderer.render(slide, graphics, area, observer),
            "Render should not throw an exception");
    }
    
    @Test
    public void testRenderWithNullSlide() {
        assertThrows(NullPointerException.class, () -> 
            renderer.render(null, graphics, area, observer),
            "Render should throw NullPointerException with null slide");
    }
    
    @Test
    public void testRenderWithNullGraphics() {
        assertThrows(NullPointerException.class, () -> 
            renderer.render(slide, null, area, observer),
            "Render should throw NullPointerException with null graphics");
    }
    
    @Test
    public void testRenderWithNullArea() {
        assertThrows(NullPointerException.class, () -> 
            renderer.render(slide, graphics, null, observer),
            "Render should throw NullPointerException with null area");
    }
    
    @Test
    public void testRenderWithEmptySlide() {
        Slide emptySlide = new Slide();
        emptySlide.setTitle("");
        
        assertDoesNotThrow(() -> renderer.render(emptySlide, graphics, area, observer),
            "Render should handle empty slide without throwing exception");
    }
} 