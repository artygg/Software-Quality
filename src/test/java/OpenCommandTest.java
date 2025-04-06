import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OpenCommandTest {

    static {
        System.setProperty("java.awt.headless", "true");
    }

    private Frame parent;
    private FileOpenController testController;
    private OpenCommand command;

    @BeforeEach
    void setUp() {
        parent = mock(Frame.class);
        testController = mock(FileOpenController.class);
        command = new OpenCommand(parent, testController);
    }

    @Test
    public void execute_cancelledSelection_shouldNotCallRepaint() {
        when(testController.chooseFileToOpen(parent)).thenReturn(null);
        command.execute();
        verify(parent, never()).repaint();
    }

    @Test
    public void execute_validFile_shouldClearLoadResetSlideAndCallRepaint() throws IOException {
        File tempFile = File.createTempFile("test", ".xml");
        tempFile.deleteOnExit();
        String validXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<presentation>" +
                "<showtitle>Test Presentation</showtitle>" +
                "<slide>" +
                "<title>Test Slide</title>" +
                "<item kind=\"text\" level=\"1\">Test Text</item>" +
                "</slide>" +
                "</presentation>";
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(validXML);
        }
        when(testController.chooseFileToOpen(parent)).thenReturn(tempFile);
        Presentation presentation = Presentation.getInstance();
        presentation.clear();
        presentation.setSlideNumber(5);
        try {
            command.execute();
        } catch (HeadlessException he) {
        }
        assertEquals(0, presentation.getCurrentSlideNumber(), "Slide number should be reset to 0");
        verify(parent, times(1)).repaint();
        presentation.clear();
    }

    @Test
    public void execute_invalidFile_shouldNotCallRepaintAndShowError() throws IOException {
        File tempFile = File.createTempFile("invalid", ".xml");
        tempFile.deleteOnExit();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("This is not valid XML");
        }
        when(testController.chooseFileToOpen(parent)).thenReturn(tempFile);
        try {
            command.execute();
        } catch (HeadlessException he) {
        }
        verify(parent, never()).repaint();
    }
}
