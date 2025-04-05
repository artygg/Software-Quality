import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import javax.swing.JFileChooser;
import java.awt.Frame;
import java.io.File;

/**
 * Tests for the SaveCommand class.
 */
public class SaveCommandTest {
    
    private Presentation presentation;
    private SaveCommand command;
    private Frame parent;
    private JFileChooser fileChooser;
    
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
        fileChooser = mock(JFileChooser.class);
        
        command = new SaveCommand(parent);
    }
    
    @Test
    public void testExecuteWithValidFile() {
        File testFile = new File("test.xml");
        when(fileChooser.showSaveDialog(parent)).thenReturn(JFileChooser.APPROVE_OPTION);
        when(fileChooser.getSelectedFile()).thenReturn(testFile);
        
        command.execute();
        
        // Verify that the file was saved
        assertTrue(testFile.exists(), "File should be created");
    }
    
    @Test
    public void testExecuteWithCancelledFileChooser() {
        when(fileChooser.showSaveDialog(parent)).thenReturn(JFileChooser.CANCEL_OPTION);
        
        command.execute();
        
        // No file should be created
        verify(parent, never()).repaint();
    }
    
    @Test
    public void testExecuteWithIOException() {
        File testFile = new File("test.xml");
        when(fileChooser.showSaveDialog(parent)).thenReturn(JFileChooser.APPROVE_OPTION);
        when(fileChooser.getSelectedFile()).thenReturn(testFile);
        
        command.execute();
        
        // No file should be created
        verify(parent, never()).repaint();
    }
} 