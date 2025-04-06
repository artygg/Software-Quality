import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NavigationControllerTest {

    private Presentation presentation;
    private NavigationController controller;

    private class TestNavigationController implements NavigationController {
        @Override
        public void nextSlide() {
            int current = presentation.getCurrentSlideNumber();
            if (current < presentation.getSize() - 1) {
                presentation.setSlideNumber(current + 1);
            }
        }

        @Override
        public void prevSlide() {
            int current = presentation.getCurrentSlideNumber();
            if (current > 0) {
                presentation.setSlideNumber(current - 1);
            }
        }

        @Override
        public void setSlideNumber(int number) {
            presentation.setSlideNumber(number);
        }

        @Override
        public int getCurrentSlideNumber() {
            return presentation.getCurrentSlideNumber();
        }
    }

    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        for (int i = 0; i < 3; i++) {
            Slide slide = new Slide();
            slide.setTitle("Slide " + (i + 1));
            presentation.addSlide(slide);
        }
        controller = new TestNavigationController();
    }

    @Test
    public void nextSlide_validCondition_shouldIncreaseSlideNumber() {
        presentation.setSlideNumber(0);
        controller.nextSlide();
        assertEquals(1, presentation.getCurrentSlideNumber(), "Should move to next slide");
    }

    @Test
    public void prevSlide_validCondition_shouldDecreaseSlideNumber() {
        presentation.setSlideNumber(1);
        controller.prevSlide();
        assertEquals(0, presentation.getCurrentSlideNumber(), "Should move to previous slide");
    }

    @Test
    public void nextSlide_atLastSlide_shouldRemainAtLastSlide() {
        presentation.setSlideNumber(2);
        controller.nextSlide();
        assertEquals(2, presentation.getCurrentSlideNumber(), "Should stay at last slide");
    }

    @Test
    public void prevSlide_atFirstSlide_shouldRemainAtFirstSlide() {
        presentation.setSlideNumber(0);
        controller.prevSlide();
        assertEquals(0, presentation.getCurrentSlideNumber(), "Should stay at first slide");
    }
}
