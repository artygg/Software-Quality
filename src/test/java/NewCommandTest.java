import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Frame;

/**
 * Tests for the NewCommand class.
 */
public class NewCommandTest {
    
    private Presentation presentation;
    private NewCommand command;
    private Frame parent;
    
    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Add a test slide
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        
        parent = mock(Frame.class);
        
        command = new NewCommand(parent);
    }
    
    @Test
    public void testExecute() {
        command.execute();
        
        assertEquals(0, presentation.getSize(), "Presentation should be empty");
        verify(parent).repaint();
    }
    
    @Test
    public void testExecuteWithEmptyPresentation() {
        presentation.clear();
        
        command.execute();
        
        assertEquals(0, presentation.getSize(), "Presentation should remain empty");
        verify(parent).repaint();
    }
} 