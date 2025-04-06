import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NextSlideCommandTest {

    private Presentation presentation;
    private NextSlideCommand command;

    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        for (int i = 0; i < 3; i++) {
            Slide slide = new Slide();
            slide.setTitle("Slide " + (i + 1));
            presentation.addSlide(slide);
        }
        command = new NextSlideCommand();
    }

    @Test
    public void execute_fromMiddleSlide_shouldMoveToNextSlide() {
        presentation.setSlideNumber(1);
        command.execute();
        assertEquals(2, presentation.getCurrentSlideNumber(), "Should move to next slide");
    }

    @Test
    public void execute_fromFirstSlide_shouldMoveToNextSlide() {
        presentation.setSlideNumber(0);
        command.execute();
        assertEquals(1, presentation.getCurrentSlideNumber(), "Should move to next slide");
    }

    @Test
    public void execute_fromLastSlide_shouldRemainAtLastSlide() {
        presentation.setSlideNumber(2);
        command.execute();
        assertEquals(2, presentation.getCurrentSlideNumber(), "Should stay at last slide");
    }
}
