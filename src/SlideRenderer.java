import java.awt.*;
import java.awt.image.ImageObserver;

public interface SlideRenderer
{
    public void render(Slide slide, Graphics g, Rectangle area, ImageObserver obs);
}
