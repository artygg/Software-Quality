import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the DemoPresentation class.
 */
public class DemoPresentationTest {
    
    private Presentation presentation;
    private DemoPresentation demoPresentation;
    
    @BeforeEach
    public void setUp() {
        // Reset the singleton instance for each test
        presentation = Presentation.getInstance();
        presentation.clear();
        demoPresentation = new DemoPresentation();
    }
    
    @Test
    public void testLoadDemoPresentation() {
        // Load the demo presentation
        demoPresentation.loadFile(presentation, null);
        
        // Verify that the presentation has the expected content
        assertEquals("Demo Presentation", presentation.getTitle(), "Title should be 'Demo Presentation'");
        assertTrue(presentation.getSize() > 0, "Presentation should have at least one slide");
        
        // Check the first slide
        Slide firstSlide = presentation.getSlide(0);
        assertNotNull(firstSlide, "First slide should not be null");
        assertEquals("JabberPoint", firstSlide.getTitle(), "First slide title should be 'JabberPoint'");
        
        // Check that the slide has items
        assertTrue(firstSlide.getSlideItems().size() > 0, "First slide should have at least one item");
    }
    
    @Test
    public void testDemoPresentationContent() {
        // Load the demo presentation
        demoPresentation.loadFile(presentation, null);
        
        // Check that the presentation has the expected number of slides
        // The exact number may vary, but it should be at least 3
        assertTrue(presentation.getSize() >= 3, "Demo presentation should have at least 3 slides");
        
        // Check that each slide has a title
        for (int i = 0; i < presentation.getSize(); i++) {
            Slide slide = presentation.getSlide(i);
            assertNotNull(slide.getTitle(), "Slide " + i + " should have a title");
            assertFalse(slide.getTitle().isEmpty(), "Slide " + i + " title should not be empty");
        }
    }
} 