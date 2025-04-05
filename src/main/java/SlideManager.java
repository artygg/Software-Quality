import java.util.List;

/**
 * Interface for managing slides in a presentation.
 * This follows the Single Responsibility Principle by focusing only on slide management.
 */
public interface SlideManager {
    void addSlide(Slide slide);
    Slide getSlide(int number);
    Slide getCurrentSlide();
    int getSize();
    void clear();
    String getTitle();
    void setTitle(String title);
} 