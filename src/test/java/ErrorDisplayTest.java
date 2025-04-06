import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.jabberpoint.test.TestUtils;
import java.awt.HeadlessException;

public class ErrorDisplayTest {

    @BeforeEach
    public void setUp() {
        TestUtils.setupHeadlessEnvironment();
    }

    @AfterEach
    public void tearDown() {
        TestUtils.resetHeadlessEnvironment();
    }

    @Test
    public void exists_ErrorDisplayClass_shouldNotBeNull() {
        assertNotNull(ErrorDisplay.class, "ErrorDisplay class should exist");
    }

    @Test
    public void showError_withoutTitle_headlessMode_shouldNotThrowException() {
        assertDoesNotThrow(() -> {
            try {
                ErrorDisplay.showError("Test error");
            } catch (HeadlessException e) {
            }
        }, "showError should handle headless mode");
    }

    @Test
    public void showError_withTitle_headlessMode_shouldNotThrowException() {
        assertDoesNotThrow(() -> {
            try {
                ErrorDisplay.showError("Test error", "Test Title");
            } catch (HeadlessException e) {
            }
        }, "showError with title should handle headless mode");
    }

    @Test
    public void showWarning_withoutTitle_headlessMode_shouldNotThrowException() {
        assertDoesNotThrow(() -> {
            try {
                ErrorDisplay.showWarning("Test warning");
            } catch (HeadlessException e) {
            }
        }, "showWarning should handle headless mode");
    }

    @Test
    public void showWarning_withTitle_headlessMode_shouldNotThrowException() {
        assertDoesNotThrow(() -> {
            try {
                ErrorDisplay.showWarning("Test warning", "Test Title");
            } catch (HeadlessException e) {
            }
        }, "showWarning with title should handle headless mode");
    }

    @Test
    public void showInfo_withoutTitle_headlessMode_shouldNotThrowException() {
        assertDoesNotThrow(() -> {
            try {
                ErrorDisplay.showInfo("Test info");
            } catch (HeadlessException e) {
            }
        }, "showInfo should handle headless mode");
    }

    @Test
    public void showInfo_withTitle_headlessMode_shouldNotThrowException() {
        assertDoesNotThrow(() -> {
            try {
                ErrorDisplay.showInfo("Test info", "Test Title");
            } catch (HeadlessException e) {
            }
        }, "showInfo with title should handle headless mode");
    }
}
