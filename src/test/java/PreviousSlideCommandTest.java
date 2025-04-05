import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the PreviousSlideCommand class.
 */
public class PreviousSlideCommandTest {
    
    private Presentation presentation;
    private PreviousSlideCommand command;
    
    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Add some slides for testing
        for (int i = 0; i < 3; i++) {
            Slide slide = new Slide();
            slide.setTitle("Slide " + (i + 1));
            presentation.addSlide(slide);
        }
        
        command = new PreviousSlideCommand();
    }
    
    @Test
    public void testExecuteFromMiddleSlide() {
        // Start at slide 1
        presentation.setSlideNumber(1);
        
        command.execute();
        
        assertEquals(0, presentation.getCurrentSlideNumber(), "Should move to previous slide");
    }
    
    @Test
    public void testExecuteFromFirstSlide() {
        // Start at first slide
        presentation.setSlideNumber(0);
        
        command.execute();
        
        assertEquals(0, presentation.getCurrentSlideNumber(), "Should stay at first slide");
    }
    
    @Test
    public void testExecuteFromLastSlide() {
        // Start at last slide
        presentation.setSlideNumber(2);
        
        command.execute();
        
        assertEquals(1, presentation.getCurrentSlideNumber(), "Should move to previous slide");
    }
} 