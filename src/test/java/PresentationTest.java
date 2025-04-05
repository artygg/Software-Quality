import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Presentation class.
 */
public class PresentationTest {
    
    private Presentation presentation;
    
    @BeforeEach
    public void setUp() {
        // Reset the singleton instance for each test
        presentation = Presentation.getInstance();
        presentation.clear();
    }
    
    @Test
    public void testSingletonInstance() {
        Presentation instance1 = Presentation.getInstance();
        Presentation instance2 = Presentation.getInstance();
        assertSame(instance1, instance2, "Singleton instances should be the same");
    }
    
    @Test
    public void testAddSlide() {
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        
        presentation.addSlide(slide);
        
        assertEquals(1, presentation.getSize(), "Presentation should have 1 slide");
        assertSame(slide, presentation.getSlide(0), "Added slide should be retrievable");
    }
    
    @Test
    public void testGetCurrentSlide() {
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        
        presentation.addSlide(slide1);
        presentation.addSlide(slide2);
        
        assertSame(slide1, presentation.getCurrentSlide(), "Current slide should be the first slide");
        
        presentation.setSlideNumber(1);
        assertSame(slide2, presentation.getCurrentSlide(), "Current slide should be the second slide");
    }
    
    @Test
    public void testSetSlideNumber() {
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        
        presentation.addSlide(slide1);
        presentation.addSlide(slide2);
        
        presentation.setSlideNumber(1);
        assertEquals(1, presentation.getCurrentSlideNumber(), "Current slide number should be 1");
        
        presentation.setSlideNumber(0);
        assertEquals(0, presentation.getCurrentSlideNumber(), "Current slide number should be 0");
    }
    
    @Test
    public void testNextSlide() {
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        
        presentation.addSlide(slide1);
        presentation.addSlide(slide2);
        
        presentation.nextSlide();
        assertEquals(1, presentation.getCurrentSlideNumber(), "Current slide number should be 1 after nextSlide()");
        
        presentation.nextSlide();
        assertEquals(1, presentation.getCurrentSlideNumber(), "Current slide number should not exceed the last slide");
    }
    
    @Test
    public void testPrevSlide() {
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        
        presentation.addSlide(slide1);
        presentation.addSlide(slide2);
        
        presentation.setSlideNumber(1);
        presentation.prevSlide();
        assertEquals(0, presentation.getCurrentSlideNumber(), "Current slide number should be 0 after prevSlide()");
        
        presentation.prevSlide();
        assertEquals(0, presentation.getCurrentSlideNumber(), "Current slide number should not be negative");
    }
    
    @Test
    public void testSetTitle() {
        presentation.setTitle("Test Presentation");
        assertEquals("Test Presentation", presentation.getTitle(), "Presentation title should be set correctly");
    }
    
    @Test
    public void testClear() {
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        
        presentation.addSlide(slide);
        presentation.setTitle("Test Presentation");
        
        presentation.clear();
        
        assertEquals(0, presentation.getSize(), "Presentation should have 0 slides after clear()");
        assertEquals("", presentation.getTitle(), "Presentation title should be empty after clear()");
    }
} 