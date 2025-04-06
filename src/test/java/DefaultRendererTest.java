import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.ImageObserver;

/**
 * Tests for the DefaultRenderer class.
 */
public class DefaultRendererTest {
    
    private DefaultRenderer renderer;
    private Slide slide;
    private Graphics2D graphics2D;
    private Rectangle area;
    private ImageObserver observer;
    
    @BeforeEach
    public void setUp() {
        renderer = new DefaultRenderer();
        slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");

        this.graphics2D = mock(Graphics2D.class);
        area = new Rectangle(0, 0, 800, 600);
        observer = mock(ImageObserver.class);
        Style.createStyles();
        FontRenderContext frc = new FontRenderContext(null, false, false);
        when(graphics2D.getFontRenderContext()).thenReturn(frc);
    }
    
    @Test
    public void testRender() {
        // Test that render doesn't throw an exception
        assertDoesNotThrow(() -> renderer.render(slide, graphics2D, area, observer),
            "Render should not throw an exception");
    }
    
    @Test
    public void testRenderWithNullSlide() {
        assertThrows(NullPointerException.class, () -> 
            renderer.render(null, graphics2D, area, observer),
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
            renderer.render(slide, graphics2D, null, observer),
            "Render should throw NullPointerException with null area");
    }
} 