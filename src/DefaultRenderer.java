import java.awt.*;
import java.awt.image.ImageObserver;

public class DefaultRenderer implements SlideRenderer
{
    @Override
    public void render(Slide slide, Graphics g, Rectangle area, ImageObserver obs)
    {
        slide.draw(g, area, obs);
    }
}
