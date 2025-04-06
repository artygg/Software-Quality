import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;

public class ExitCommandTest {

    @Test
    public void constructor_injectedController_shouldCreateInstance() {
        AppController mockController = mock(AppController.class);
        ExitCommand command = new ExitCommand(mockController);
        assertNotNull(command, "ExitCommand should be created successfully");
    }

    @Test
    public void execute_injectedController_shouldCallShutdown() {
        AppController mockController = mock(AppController.class);
        ExitCommand command = new ExitCommand(mockController);
        command.execute();
        verify(mockController, times(1)).shutdown();
    }

    @Test
    public void constructor_defaultConstructor_shouldUseProductionAppController() throws Exception {
        Assumptions.assumeFalse(Boolean.getBoolean("java.awt.headless"), "Skipping test in headless mode");
        ExitCommand command = new ExitCommand();
        Field appControllerField = ExitCommand.class.getDeclaredField("appController");
        appControllerField.setAccessible(true);
        AppController controller = (AppController) appControllerField.get(command);
        assertNotNull(controller, "AppController in default constructor should not be null");
        assertTrue(controller instanceof ProductionAppController, "AppController should be an instance of ProductionAppController");
    }

    @Test
    public void execute_defaultConstructor_replacedController_shouldCallShutdown() throws Exception {
        Assumptions.assumeFalse(Boolean.getBoolean("java.awt.headless"), "Skipping test in headless mode");
        ExitCommand command = new ExitCommand();
        AppController mockController = mock(AppController.class);
        Field appControllerField = ExitCommand.class.getDeclaredField("appController");
        appControllerField.setAccessible(true);
        appControllerField.set(command, mockController);
        command.execute();
        verify(mockController, times(1)).shutdown();
    }
}
