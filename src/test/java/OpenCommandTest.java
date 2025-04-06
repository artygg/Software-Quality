import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Frame;
import java.io.File;

public class OpenCommandTest {

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
    public void testExecuteWithCancelledSelection() {
        when(testController.chooseFileToOpen(parent)).thenReturn(null);

        command.execute();

        // No file selected => no repaint
        verify(parent, never()).repaint();
    }
}
