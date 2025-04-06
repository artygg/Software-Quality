import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlideManagerTest {

    private Presentation presentation;
    private SlideManager manager;

    private class TestSlideManager implements SlideManager {
        @Override
        public void addSlide(Slide slide) {
            presentation.addSlide(slide);
        }

        @Override
        public Slide getSlide(int number) {
            return presentation.getSlide(number);
        }

        @Override
        public Slide getCurrentSlide() {
            return presentation.getCurrentSlide();
        }

        @Override
        public int getSize() {
            return presentation.getSize();
        }

        @Override
        public void clear() {
            presentation.clear();
        }

        @Override
        public String getTitle() {
            return presentation.getTitle();
        }

        @Override
        public void setTitle(String title) {
            presentation.setTitle(title);
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
        manager = new TestSlideManager();
    }

    @Test
    public void addSlide_validSlide_shouldIncreaseCountAndBeRetrievable() {
        Slide newSlide = new Slide();
        newSlide.setTitle("New Slide");
        manager.addSlide(newSlide);
        assertEquals(4, presentation.getSize(), "Slide count should increase");
        assertEquals(newSlide, presentation.getSlide(3), "New slide should be added");
    }

    @Test
    public void getCurrentSlide_setSlideNumber_shouldReturnCorrectSlide() {
        presentation.setSlideNumber(1);
        Slide currentSlide = manager.getCurrentSlide();
        assertNotNull(currentSlide, "Current slide should not be null");
        assertEquals("Slide 2", currentSlide.getTitle(), "Should return correct slide");
    }

    @Test
    public void getSize_called_shouldReturnCorrectSlideCount() {
        assertEquals(3, manager.getSize(), "Should return correct slide count");
    }
}
