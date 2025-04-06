import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Tests for the XMLAccessor class.
 */
public class XMLAccessorTest {
    
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
    public void testLoadFile() throws IOException {
        // Create a test XML file
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("<?xml version=\"1.0\"?>");
            writer.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
            writer.println("<presentation>");
            writer.println("<showtitle>Test Presentation</showtitle>");
            writer.println("<slide>");
            writer.println("<title>Test Slide</title>");
            writer.println("<item kind=\"text\" level=\"1\">Test Text</item>");
            writer.println("</slide>");
            writer.println("</presentation>");
        }
        
        // Load the file
        accessor.loadFile(presentation, tempFile.getAbsolutePath());
        
        // Verify the presentation was loaded correctly
        assertEquals("Test Presentation", presentation.getTitle(), "Title should be loaded");
        assertEquals(1, presentation.getSize(), "Should have one slide");
        
        Slide slide = presentation.getSlide(0);
        assertEquals("Test Slide", slide.getTitle(), "Slide title should be loaded");
        assertEquals(1, slide.getSlideItems().size(), "Slide should have one item");
    }
    
    @Test
    public void testSaveFile() throws IOException {
        // Create a test presentation
        presentation.setTitle("Test Presentation");
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        
        // Save the presentation
        accessor.saveFile(presentation, tempFile.getAbsolutePath());
        
        // Verify the file was created
        assertTrue(tempFile.exists(), "File should be created");
        assertTrue(tempFile.length() > 0, "File should not be empty");
    }
    
    @Test
    public void testLoadFileWithInvalidXML() throws IOException {
        // Create an invalid XML file
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("This is not valid XML");
        }
        
        // Attempt to load the file
        assertThrows(IOException.class, () -> 
            accessor.loadFile(presentation, tempFile.getAbsolutePath()),
            "Should throw IOException for invalid XML");
    }
    
    @Test
    public void testLoadFileWithNonexistentFile() {
        assertThrows(IOException.class, () -> 
            accessor.loadFile(presentation, "nonexistent.xml"),
            "Should throw IOException for nonexistent file");
    }
    
    @Test
    public void testSaveFileWithInvalidPath() {
        presentation.setTitle("Test Presentation");
        
        assertThrows(IOException.class, () -> 
            accessor.saveFile(presentation, "/invalid/path/test.xml"),
            "Should throw IOException for invalid path");
    }
} 