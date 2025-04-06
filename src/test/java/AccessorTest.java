import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.awt.HeadlessException;

import org.jabberpoint.test.TestUtils;

/**
 * Tests for the Accessor class.
 */
public class AccessorTest {
    
    private Accessor accessor;
    private Presentation presentation;
    private File tempFile;
    
    @BeforeEach
    public void setUp() throws IOException {
        // Setup headless environment
        TestUtils.setupHeadlessEnvironment();
        
        accessor = new XMLAccessor();
        presentation = Presentation.getInstance();
        presentation.clear();
        
        // Create a temporary file for testing
        tempFile = File.createTempFile("test", ".xml");
        tempFile.deleteOnExit();
    }
    
    @AfterEach
    public void tearDown() {
        // Reset headless environment
        TestUtils.resetHeadlessEnvironment();
        
        // Clean up temp file
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }
    
    @Test
    public void testLoadFile() throws IOException {
        // Create a test XML file
        try (java.io.PrintWriter writer = new java.io.PrintWriter(tempFile)) {
            writer.println("<?xml version=\"1.0\"?>");
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
        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(1, presentation.getSize());
        
        Slide slide = presentation.getSlide(0);
        assertEquals("Test Slide", slide.getTitle());
        assertEquals(1, slide.getSize());
    }
    
    @Test
    public void testLoadNonExistentFile() {
        // Attempt to load a nonexistent file
        try {
            accessor.loadFile(presentation, "nonexistent.xml");
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            // Expected exception
            assertTrue(e.getMessage().contains("nonexistent.xml") || 
                      e.getMessage().contains("No such file"),
                      "Exception message should mention the nonexistent file");
        } catch (HeadlessException e) {
            // Expected in headless mode
        }
    }
    
    @Test
    public void testLoadInvalidXML() throws IOException {
        // Create an invalid XML file
        try (java.io.PrintWriter writer = new java.io.PrintWriter(tempFile)) {
            writer.println("This is not valid XML");
        }
        
        // Attempt to load the file
        try {
            accessor.loadFile(presentation, tempFile.getAbsolutePath());
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            // Expected exception
            assertTrue(e.getMessage().contains("Content is not allowed in prolog") ||
                      e.getMessage().contains("Premature end of file"),
                      "Exception message should indicate XML parsing error");
        } catch (HeadlessException e) {
            // Expected in headless mode
        }
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
        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);
    }
    
    @Test
    public void testSaveFileWithInvalidPath() {
        try {
            accessor.saveFile(presentation, "/invalid/path/test.xml");
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            // Expected exception
        } catch (HeadlessException e) {
            // Expected in headless mode
        }
    }
    
    @Test
    public void testLoadFileWithEmptyFile() throws IOException {
        // Create an empty file
        tempFile.createNewFile();
        
        // Attempt to load the file
        try {
            accessor.loadFile(presentation, tempFile.getAbsolutePath());
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            // Expected exception
            assertTrue(e.getMessage().contains("Premature end of file"),
                      "Exception message should indicate premature end of file");
        } catch (HeadlessException e) {
            // Expected in headless mode
        }
    }
    
    @Test
    public void testLoadFileWithMissingSlides() throws IOException {
        // Create an XML file without any slides
        try (java.io.PrintWriter writer = new java.io.PrintWriter(tempFile)) {
            writer.println("<?xml version=\"1.0\"?>");
            writer.println("<presentation>");
            writer.println("<showtitle>Test Presentation</showtitle>");
            writer.println("</presentation>");
        }
        
        // Load the file
        accessor.loadFile(presentation, tempFile.getAbsolutePath());
        
        // Verify the presentation was loaded correctly
        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(0, presentation.getSize());
    }
} 