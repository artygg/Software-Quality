import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import javax.swing.JFileChooser;
import java.awt.Frame;
import java.io.File;

/**
 * Tests for the OpenCommand class.
 */
public class OpenCommandTest {
    
    private Presentation presentation;
    private OpenCommand command;
    private Frame parent;
    private JFileChooser fileChooser;
    
    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        
        parent = mock(Frame.class);
        fileChooser = mock(JFileChooser.class);
        
        command = new OpenCommand(parent);
    }
    
    @Test
    public void testExecuteWithValidFile() {
        File testFile = new File("test.xml");
        when(fileChooser.showOpenDialog(parent)).thenReturn(JFileChooser.APPROVE_OPTION);
        when(fileChooser.getSelectedFile()).thenReturn(testFile);
        
        command.execute();
        
        verify(parent).repaint();
    }
    
    @Test
    public void testExecuteWithCancelledFileChooser() {
        when(fileChooser.showOpenDialog(parent)).thenReturn(JFileChooser.CANCEL_OPTION);
        
        command.execute();
        
        verify(parent, never()).repaint();
    }
    
    @Test
    public void testExecuteWithIOException() {
        File testFile = new File("test.xml");
        when(fileChooser.showOpenDialog(parent)).thenReturn(JFileChooser.APPROVE_OPTION);
        when(fileChooser.getSelectedFile()).thenReturn(testFile);
        
        command.execute();
        
        verify(parent, never()).repaint();
    }
} 