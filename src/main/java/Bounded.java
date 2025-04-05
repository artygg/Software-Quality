import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 Interface for objects that have a bounding box.
 This interface focuses solely on the bounding box calculation functionality.
 */
public interface Bounded {
    Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);
} 