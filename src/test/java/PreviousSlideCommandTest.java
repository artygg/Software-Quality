import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PreviousSlideCommandTest {

    private Presentation presentation;
    private PreviousSlideCommand command;

    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        for (int i = 0; i < 3; i++) {
            Slide slide = new Slide();
            slide.setTitle("Slide " + (i + 1));
            presentation.addSlide(slide);
        }
        command = new PreviousSlideCommand();
    }

    @Test
    public void execute_fromMiddleSlide_shouldDecreaseSlideNumber() {
        presentation.setSlideNumber(1);
        command.execute();
        assertEquals(0, presentation.getCurrentSlideNumber(), "Should move to previous slide");
    }

    @Test
    public void execute_fromFirstSlide_shouldRemainAtFirstSlide() {
        presentation.setSlideNumber(0);
        command.execute();
        assertEquals(0, presentation.getCurrentSlideNumber(), "Should stay at first slide");
    }

    @Test
    public void execute_fromLastSlide_shouldDecreaseSlideNumber() {
        presentation.setSlideNumber(2);
        command.execute();
        assertEquals(1, presentation.getCurrentSlideNumber(), "Should move to previous slide");
    }
}
