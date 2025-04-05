import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

/**
 * Tests for the Accessor interface and its implementation.
 */
public class AccessorTest {
    
    private XMLAccessor accessor;
    private Presentation presentation;
    private File tempFile;
    
    @BeforeEach
    public void setUp() throws IOException {
        accessor = new XMLAccessor();
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Create a temporary file for testing
        tempFile = File.createTempFile("test", ".xml");
        tempFile.deleteOnExit();
    }
    
    @Test
    public void testAccessorInterface() {
        // Verify that XMLAccessor implements the Accessor interface
        assertTrue(accessor instanceof Accessor, "XMLAccessor should implement the Accessor interface");
    }
    
    @Test
    public void testLoadFile() throws IOException {
        // Set up a presentation with some content
        presentation.setTitle("Test Presentation");
        
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        
        // Save the presentation
        accessor.saveFile(presentation, tempFile.getAbsolutePath());
        
        // Create a new presentation and load the saved file
        Presentation loadedPresentation = Presentation.getInstance();
        loadedPresentation.clear();
        accessor.loadFile(loadedPresentation, tempFile.getAbsolutePath());
        
        // Verify that the loaded presentation matches the original
        assertEquals("Test Presentation", loadedPresentation.getTitle(), "Title should match");
        assertEquals(1, loadedPresentation.getSize(), "Should have 1 slide");
        
        Slide loadedSlide = loadedPresentation.getSlide(0);
        assertEquals("Test Slide", loadedSlide.getTitle(), "Slide title should match");
    }
    
    @Test
    public void testSaveFile() throws IOException {
        // Set up a presentation with some content
        presentation.setTitle("Test Presentation");
        
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        
        // Save the presentation
        accessor.saveFile(presentation, tempFile.getAbsolutePath());
        
        // Verify that the file was created
        assertTrue(tempFile.exists(), "File should be created");
        assertTrue(tempFile.length() > 0, "File should not be empty");
    }
    
    @Test
    public void testLoadNonExistentFile() {
        // Try to load a non-existent file
        assertThrows(IOException.class, () -> {
            accessor.loadFile(presentation, "non_existent_file.xml");
        }, "Loading a non-existent file should throw an IOException");
    }
    
    @Test
    public void testSaveToInvalidPath() {
        // Try to save to an invalid path
        assertThrows(IOException.class, () -> {
            accessor.saveFile(presentation, "/invalid/path/file.xml");
        }, "Saving to an invalid path should throw an IOException");
    }
    
    @Test
    public void testGetDemoAccessor() {
        Accessor accessor = Accessor.getDemoAccessor();
        assertNotNull(accessor, "Demo accessor should not be null");
        assertTrue(accessor instanceof DemoPresentation, "Demo accessor should be a DemoPresentation");
    }
    
    @Test
    public void testConstants() {
        assertEquals("Demonstration presentation", Accessor.DEMO_NAME, "DEMO_NAME should be correct");
        assertEquals(".xml", Accessor.DEFAULT_EXTENSION, "DEFAULT_EXTENSION should be correct");
    }
    
    @Test
    public void testAbstractMethods() {
        // Create a concrete implementation of Accessor for testing
        Accessor testAccessor = new Accessor() {
            @Override
            public void loadFile(Presentation p, String fn) throws IOException {
                // Empty implementation
            }
            
            @Override
            public void saveFile(Presentation p, String fn) throws IOException {
                // Empty implementation
            }
        };
        
        assertNotNull(testAccessor, "Test accessor should be created");
    }
} 