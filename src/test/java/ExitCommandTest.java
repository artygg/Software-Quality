import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ExitCommand class.
 */
public class ExitCommandTest {
    
    @Test
    public void testExitCommandCreation() {
        ExitCommand command = new ExitCommand();
        assertNotNull(command, "ExitCommand should be created successfully");
    }
    
    @Test
    public void testExecute() {
        ExitCommand command = new ExitCommand();
        
        // We can't easily test System.exit() in a unit test
        // The best we can do is verify that the command can be created and executed
        // without throwing an exception
        assertDoesNotThrow(() -> command.execute(), 
            "Execute should not throw an exception");
    }
} 