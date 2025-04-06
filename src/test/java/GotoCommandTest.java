import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import javax.swing.JOptionPane;
import org.mockito.MockedStatic;

public class GotoCommandTest {

    private Presentation presentation;
    private GotoCommand command;

    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        for (int i = 0; i < 3; i++) {
            Slide slide = new Slide();
            slide.setTitle("Slide " + (i + 1));
            presentation.addSlide(slide);
        }
        command = new GotoCommand();
    }

    @Test
    public void execute_validSlideNumber_shouldUpdateCurrentSlideNumber() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(anyString()))
                    .thenReturn("2");
            command.execute();
            assertEquals(1, presentation.getCurrentSlideNumber(), "Current slide number should be updated");
        }
    }

    @Test
    public void execute_invalidSlideNumber_shouldNotChangeCurrentSlideNumber_andShowError() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(anyString()))
                    .thenReturn("5");
            command.execute();
            assertEquals(0, presentation.getCurrentSlideNumber(), "Current slide number should not change");
            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(
                    any(),
                    contains("Invalid page number"),
                    eq("Invalid Input"),
                    eq(JOptionPane.ERROR_MESSAGE)
            ));
        }
    }

    @Test
    public void execute_nonNumericInput_shouldNotChangeCurrentSlideNumber_andShowInputError() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(anyString()))
                    .thenReturn("abc");
            command.execute();
            assertEquals(0, presentation.getCurrentSlideNumber(), "Current slide number should not change");
            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(
                    any(),
                    contains("Please enter a valid number"),
                    eq("Input Error"),
                    eq(JOptionPane.ERROR_MESSAGE)
            ));
        }
    }
}
