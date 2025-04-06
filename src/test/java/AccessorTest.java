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
        assertTrue(accessor instanceof Accessor, "XMLAccessor should implement the Accessor interface");
    }
    
    @Test
    public void testLoadFile() throws IOException {
        presentation.setTitle("Test Presentation");
        
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);

        accessor.saveFile(presentation, tempFile.getAbsolutePath());
        Presentation loadedPresentation = Presentation.getInstance();
        loadedPresentation.clear();
        accessor.loadFile(loadedPresentation, tempFile.getAbsolutePath());

        assertEquals("Test Presentation", loadedPresentation.getTitle(), "Title should match");
        assertEquals(1, loadedPresentation.getSize(), "Should have 1 slide");
        
        Slide loadedSlide = loadedPresentation.getSlide(0);
        assertEquals("Test Slide", loadedSlide.getTitle(), "Slide title should match");
    }
    
    @Test
    public void testSaveFile() throws IOException {
        presentation.setTitle("Test Presentation");
        
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);

        accessor.saveFile(presentation, tempFile.getAbsolutePath());

        assertTrue(tempFile.exists(), "File should be created");
        assertTrue(tempFile.length() > 0, "File should not be empty");
    }
    
    @Test
    public void testLoadNonExistentFile() {
        assertThrows(IOException.class, () -> {
            accessor.loadFile(presentation, "non_existent_file.xml");
        }, "Loading a non-existent file should throw an IOException");
    }
    
    @Test
    public void testSaveToInvalidPath() {
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
        Accessor testAccessor = new Accessor() {
            @Override
            public void loadFile(Presentation p, String fn) throws IOException {
            }
            
            @Override
            public void saveFile(Presentation p, String fn) throws IOException {
            }
        };
        
        assertNotNull(testAccessor, "Test accessor should be created");
    }
} 