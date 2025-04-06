import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.awt.HeadlessException;
import org.jabberpoint.test.TestUtils;

public class AccessorTest {

    private Accessor accessor;
    private Presentation presentation;
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        TestUtils.setupHeadlessEnvironment();
        accessor = new XMLAccessor();
        presentation = Presentation.getInstance();
        presentation.clear();
        tempFile = File.createTempFile("test", ".xml");
        tempFile.deleteOnExit();
    }

    @AfterEach
    public void tearDown() {
        TestUtils.resetHeadlessEnvironment();
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void loadFile_validXML_shouldLoadPresentationCorrectly() throws IOException {
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
        accessor.loadFile(presentation, tempFile.getAbsolutePath());
        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(1, presentation.getSize());
        Slide slide = presentation.getSlide(0);
        assertEquals("Test Slide", slide.getTitle());
        assertEquals(1, slide.getSize());
    }

    @Test
    public void loadFile_nonExistentFile_shouldThrowIOException() {
        try {
            accessor.loadFile(presentation, "nonexistent.xml");
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("nonexistent.xml") ||
                            e.getMessage().contains("No such file"),
                    "Exception message should mention the nonexistent file");
        } catch (HeadlessException e) {
        }
    }

    @Test
    public void loadFile_invalidXML_shouldThrowIOException() throws IOException {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(tempFile)) {
            writer.println("This is not valid XML");
        }
        try {
            accessor.loadFile(presentation, tempFile.getAbsolutePath());
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Content is not allowed in prolog") ||
                            e.getMessage().contains("Premature end of file"),
                    "Exception message should indicate XML parsing error");
        } catch (HeadlessException e) {
        }
    }

    @Test
    public void saveFile_validPresentation_shouldCreateNonEmptyFile() throws IOException {
        presentation.setTitle("Test Presentation");
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        accessor.saveFile(presentation, tempFile.getAbsolutePath());
        assertTrue(tempFile.exists());
        assertTrue(tempFile.length() > 0);
    }

    @Test
    public void saveFile_invalidPath_shouldThrowIOException() {
        try {
            accessor.saveFile(presentation, "/invalid/path/test.xml");
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
        } catch (HeadlessException e) {
        }
    }

    @Test
    public void loadFile_emptyFile_shouldThrowIOException() throws IOException {
        tempFile.createNewFile();
        try {
            accessor.loadFile(presentation, tempFile.getAbsolutePath());
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Premature end of file"),
                    "Exception message should indicate premature end of file");
        } catch (HeadlessException e) {
        }
    }

    @Test
    public void loadFile_missingSlides_shouldLoadEmptyPresentation() throws IOException {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(tempFile)) {
            writer.println("<?xml version=\"1.0\"?>");
            writer.println("<presentation>");
            writer.println("<showtitle>Test Presentation</showtitle>");
            writer.println("</presentation>");
        }
        accessor.loadFile(presentation, tempFile.getAbsolutePath());
        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(0, presentation.getSize());
    }
}
