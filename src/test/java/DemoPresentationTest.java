import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DemoPresentationTest {

    private Presentation presentation;
    private DemoPresentation demoPresentation;

    @BeforeEach
    public void setUp() {
        presentation = Presentation.getInstance();
        presentation.clear();
        demoPresentation = new DemoPresentation();
    }

    @Test
    public void loadFile_demoPresentationLoaded_shouldHaveExpectedContent() {
        demoPresentation.loadFile(presentation, null);
        assertEquals("Demo Presentation", presentation.getTitle(), "Title should be 'Demo Presentation'");
        assertTrue(presentation.getSize() > 0, "Presentation should have at least one slide");
        Slide firstSlide = presentation.getSlide(0);
        assertNotNull(firstSlide, "First slide should not be null");
        assertEquals("JabberPoint", firstSlide.getTitle(), "First slide title should be 'JabberPoint'");
        assertTrue(firstSlide.getSlideItems().size() > 0, "First slide should have at least one item");
    }

    @Test
    public void loadFile_demoPresentationLoaded_shouldHaveAtLeastThreeSlidesAndNonEmptyTitles() {
        demoPresentation.loadFile(presentation, null);
        assertTrue(presentation.getSize() >= 3, "Demo presentation should have at least 3 slides");
        for (int i = 0; i < presentation.getSize(); i++) {
            Slide slide = presentation.getSlide(i);
            assertNotNull(slide.getTitle(), "Slide " + i + " should have a title");
            assertFalse(slide.getTitle().isEmpty(), "Slide " + i + " title should not be empty");
        }
    }
}
