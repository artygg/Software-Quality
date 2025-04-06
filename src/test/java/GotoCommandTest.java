import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import javax.swing.JOptionPane;
import org.mockito.MockedStatic;

/**
 * Tests for the GotoCommand class.
 */
public class GotoCommandTest {
    
    private Presentation presentation;
    private GotoCommand command;
    
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
        
        command = new GotoCommand();
    }
    
    @Test
    public void testExecuteWithValidSlideNumber() {
        // Mock JOptionPane to return a valid slide number
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(anyString()))
                .thenReturn("2");
            
            command.execute();
            
            assertEquals(1, presentation.getCurrentSlideNumber(), "Current slide number should be updated");
        }
    }
    
    @Test
    public void testExecuteWithInvalidSlideNumber() {
        // Mock JOptionPane to return an invalid slide number
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(anyString()))
                .thenReturn("5");
            
            command.execute();
            
            assertEquals(0, presentation.getCurrentSlideNumber(), "Current slide number should not change");
            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(
                any(), 
                contains("Invalid page number"), 
                eq("Invalid Input"), 
                eq(JOptionPane.ERROR_MESSAGE)
            ));
        }
    }
    
    @Test
    public void testExecuteWithNonNumericInput() {
        // Mock JOptionPane to return non-numeric input
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(anyString()))
                .thenReturn("abc");
            
            command.execute();
            
            assertEquals(0, presentation.getCurrentSlideNumber(), "Current slide number should not change");
            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(
                any(), 
                contains("Please enter a valid number"), 
                eq("Input Error"), 
                eq(JOptionPane.ERROR_MESSAGE)
            ));
        }
    }
} 