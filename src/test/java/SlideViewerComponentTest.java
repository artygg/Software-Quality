import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * Tests for the SlideViewerComponent class.
 */
public class SlideViewerComponentTest {
    
    private SlideViewerComponent component;
    private Presentation presentation;
    private JFrame frame;
    private Slide slide;
    
    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Add a test slide
        slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        
        frame = mock(JFrame.class);
        component = new SlideViewerComponent(presentation, frame);
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(component, "Component should be created");
    }
    
    @Test
    public void testGetPreferredSize() {
        Dimension size = component.getPreferredSize();
        assertNotNull(size, "Preferred size should not be null");
        assertEquals(Slide.WIDTH, size.width, "Width should match Slide.WIDTH");
        assertEquals(Slide.HEIGHT, size.height, "Height should match Slide.HEIGHT");
    }
    
    @Test
    public void testUpdate() {
        Slide newSlide = new Slide();
        newSlide.setTitle("New Slide");
        newSlide.appendText(1, "New Text");
        
        component.update(presentation, newSlide);
        
        verify(frame).setTitle(presentation.getTitle());
    }
    
    @Test
    public void testUpdateWithNullSlide() {
        component.update(presentation, null);
        
        verify(frame, never()).setTitle(anyString());
    }
    
    @Test
    public void testPaintComponent() {
        Graphics graphics = mock(Graphics.class);
        
        assertDoesNotThrow(() -> component.paintComponent(graphics),
            "Paint should not throw an exception");
    }
    
    @Test
    public void testSetRenderer() {
        SlideRenderer newRenderer = mock(SlideRenderer.class);
        
        component.setRenderer(newRenderer);
        
        // Verify that the new renderer is used in paintComponent
        Graphics graphics = mock(Graphics.class);
        component.paintComponent(graphics);
        
        verify(newRenderer).render(any(), any(), any(), any());
    }
} 