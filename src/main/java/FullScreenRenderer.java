import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class FullScreenRenderer implements SlideRenderer
{
    @Override
    public void render(Slide slide, Graphics g, Rectangle area, ImageObserver obs)
    {
        Rectangle fullScreenArea = new Rectangle(0, 0, area.width, area.height);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();

        float scaleX = (float) fullScreenArea.width / Constants.DEFAULT_SLIDE_WIDTH;
        float scaleY = (float) fullScreenArea.height / Constants.DEFAULT_SLIDE_HEIGHT;

        float scale = Math.min(scaleX, scaleY);

        g2d.scale(scale, scale);

        Rectangle scaledArea = new Rectangle(0, 0, Constants.DEFAULT_SLIDE_WIDTH, Constants.DEFAULT_SLIDE_HEIGHT);
        slide.draw(g2d, scaledArea, obs);

        g2d.setTransform(originalTransform);

    }
}
