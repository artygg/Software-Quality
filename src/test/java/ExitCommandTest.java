import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExitCommandTest {

    @Test
    public void testExitCommandCreation() {
        // We can pass a mock or any AppController to the constructor
        AppController mockController = mock(AppController.class);
        ExitCommand command = new ExitCommand(mockController);
        assertNotNull(command, "ExitCommand should be created successfully");
    }

    @Test
    public void testExecute() {
        // Mock the AppController so it doesn't actually call System.exit(0)
        AppController mockController = mock(AppController.class);
        ExitCommand command = new ExitCommand(mockController);

        // Execute the command
        command.execute();

        // Verify that the command called appController.shutdown()
        verify(mockController, times(1)).shutdown();
    }
}
