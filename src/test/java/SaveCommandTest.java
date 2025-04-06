import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

/**
 * Tests for the SaveCommand class.
 */
public class SaveCommandTest {
    
    private SaveCommand saveCommand;
    private Presentation presentation;
    private File tempFile;
    
    @BeforeEach
    public void setUp() throws IOException {
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Create a temporary file for testing
        tempFile = File.createTempFile("test", ".xml");
        tempFile.deleteOnExit();
        
        saveCommand = new SaveCommand(presentation, tempFile.getAbsolutePath());
    }
    
    @Test
    public void testExecute() throws IOException {
        // Add some content to the presentation
        presentation.setTitle("Test Presentation");
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        
        // Execute the save command
        saveCommand.execute();
        
        // Verify the file was created
        assertTrue(tempFile.exists(), "File should be created");
        assertTrue(tempFile.length() > 0, "File should not be empty");
    }
    
    @Test
    public void testExecuteWithEmptyPresentation() throws IOException {
        // Execute the save command with an empty presentation
        saveCommand.execute();
        
        // Verify the file was created
        assertTrue(tempFile.exists(), "File should be created even for empty presentation");
    }
} 