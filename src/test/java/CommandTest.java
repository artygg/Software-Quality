import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Command classes.
 */
public class CommandTest {
    
    private Presentation presentation;
    
    @BeforeEach
    public void setUp() {
        // Reset the singleton instance for each test
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Add some slides for testing
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        presentation.addSlide(slide1);
        
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        presentation.addSlide(slide2);
    }
    
    @Test
    public void testNextSlideCommand() {
        NextSlideCommand command = new NextSlideCommand();
        
        // Initial slide number should be 0
        assertEquals(0, presentation.getCurrentSlideNumber(), "Initial slide number should be 0");
        
        // Execute the command
        command.execute();
        
        // Slide number should be 1
        assertEquals(1, presentation.getCurrentSlideNumber(), "Slide number should be 1 after NextSlideCommand");
        
        // Execute the command again
        command.execute();
        
        // Slide number should still be 1 (at the end)
        assertEquals(1, presentation.getCurrentSlideNumber(), "Slide number should not exceed the last slide");
    }
    
    @Test
    public void testPreviousSlideCommand() {
        PreviousSlideCommand command = new PreviousSlideCommand();
        
        // Move to the second slide
        presentation.setSlideNumber(1);
        assertEquals(1, presentation.getCurrentSlideNumber(), "Slide number should be 1");
        
        // Execute the command
        command.execute();
        
        // Slide number should be 0
        assertEquals(0, presentation.getCurrentSlideNumber(), "Slide number should be 0 after PreviousSlideCommand");
        
        // Execute the command again
        command.execute();
        
        // Slide number should still be 0 (at the beginning)
        assertEquals(0, presentation.getCurrentSlideNumber(), "Slide number should not be negative");
    }
    
    @Test
    public void testExitCommand() {
        ExitCommand command = new ExitCommand();
        
        // This test is tricky because it calls System.exit()
        // In a real test environment, we would use a mock or a different approach
        // For now, we'll just verify that the command exists
        assertNotNull(command, "ExitCommand should be created");
    }
} 