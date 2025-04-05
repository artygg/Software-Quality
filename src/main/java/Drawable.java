import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 Interface for objects that can be drawn on a slide.
 This interface focuses solely on the drawing functionality.
 */
public interface Drawable {
    void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
} 