import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.awt.Frame;

/**
 * Tests for the AboutBox class.
 */
public class AboutBoxTest {
    
    @Test
    public void testShow() {
        // Create a mock Frame
        Frame parent = mock(Frame.class);
        
        // Call show method
        AboutBox.show(parent);
        
        // Verify that a dialog was created and shown
        verify(parent, times(1)).setVisible(true);
    }
    
    @Test
    public void testDialogContent() {
        Frame parent = mock(Frame.class);

        AboutBox.show(parent);

        verify(parent, times(1)).setTitle("About JabberPoint");
    }
    
    @Test
    public void testDialogSize() {
        // Create a mock Frame
        Frame parent = mock(Frame.class);
        
        // Call show method
        AboutBox.show(parent);
        
        // Verify that the dialog has a reasonable size
        verify(parent, times(1)).setSize(anyInt(), anyInt());
    }
} 