import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUtilsTest {

    @Test
    public void setupHeadlessEnvironment_validCall_shouldNotThrowException() {
        assertDoesNotThrow(() -> TestUtils.setupHeadlessEnvironment(), "setupHeadlessEnvironment should not throw an exception");
    }

    @Test
    public void resetHeadlessEnvironment_validCall_shouldNotThrowException() {
        assertDoesNotThrow(() -> TestUtils.resetHeadlessEnvironment(), "resetHeadlessEnvironment should not throw an exception");
    }
}
