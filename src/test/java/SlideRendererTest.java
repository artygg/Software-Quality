import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Tests for the SlideRenderer interface and its implementations.
 */
public class SlideRendererTest {
    
    private Presentation presentation;
    private Slide slide;
    private DefaultRenderer defaultRenderer;
    private FullScreenRenderer fullScreenRenderer;
    
    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        
        slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        
        defaultRenderer = new DefaultRenderer();
        fullScreenRenderer = new FullScreenRenderer();
    }
    
    @Test
    public void testRendererInterfaces() {
        // Verify that the renderers implement the SlideRenderer interface
        assertTrue(defaultRenderer instanceof SlideRenderer, "DefaultRenderer should implement the SlideRenderer interface");
        assertTrue(fullScreenRenderer instanceof SlideRenderer, "FullScreenRenderer should implement the SlideRenderer interface");
    }
    
    @Test
    public void testDefaultRenderer() {
        // Create a mock slide
        Slide slide = mock(Slide.class);
        when(slide.getTitle()).thenReturn("Test Slide");
        
        // Create a mock graphics context
        Graphics graphics = mock(Graphics.class);
        
        // Create a mock area
        Rectangle area = new Rectangle(0, 0, 800, 600);
        
        // Create a mock image observer
        ImageObserver observer = mock(ImageObserver.class);
        
        // Test that render doesn't throw an exception
        assertDoesNotThrow(() -> defaultRenderer.render(slide, graphics, area, observer),
            "Render method should not throw an exception");
    }
    

    
    @Test
    public void testRenderWithNullSlide() {
        // Create a mock graphics context
        Graphics graphics = mock(Graphics.class);
        
        // Create a mock area
        Rectangle area = new Rectangle(0, 0, 800, 600);
        
        // Create a mock image observer
        ImageObserver observer = mock(ImageObserver.class);
        
        // Test that render throws NullPointerException with null slide
        assertThrows(NullPointerException.class, () -> defaultRenderer.render(null, graphics, area, observer),
            "Render method should throw NullPointerException with null slide");
    }
} 