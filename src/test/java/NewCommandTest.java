import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.awt.Frame;

public class NewCommandTest {

    private Presentation presentation;
    private NewCommand command;
    private Frame parent;

    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.appendText(1, "Test Text");
        presentation.addSlide(slide);
        parent = mock(Frame.class);
        command = new NewCommand(parent);
    }

    @Test
    public void execute_withNonEmptyPresentation_shouldClearPresentationAndCallRepaint() {
        command.execute();
        assertEquals(0, presentation.getSize(), "Presentation should be empty");
        verify(parent).repaint();
    }

    @Test
    public void execute_withEmptyPresentation_shouldRemainEmptyAndCallRepaint() {
        presentation.clear();
        command.execute();
        assertEquals(0, presentation.getSize(), "Presentation should remain empty");
        verify(parent).repaint();
    }
}
