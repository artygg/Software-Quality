/**
 * Interface for controlling slide navigation.
 * This follows the Single Responsibility Principle by focusing only on navigation.
 */
public interface NavigationController {
    void nextSlide();
    void prevSlide();
    void setSlideNumber(int number);
    int getCurrentSlideNumber();
} 