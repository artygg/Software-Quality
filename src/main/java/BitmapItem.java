import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/** <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items have the responsibility to draw themselves.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class BitmapItem extends SlideItem {
  private BufferedImage bufferedImage;
  private String imageName;
  
  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";

	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try {
			if (name != null && !name.isEmpty()) {
				bufferedImage = ImageIO.read(new File(imageName));
			}
		}
		catch (IOException e) {
			ErrorDisplay.showError(FILE + imageName + NOTFOUND, "Image Loading Error");
			bufferedImage = null;
		}
	}

	public BitmapItem() {
		this(0, null);
	}

	public String getName() {
		return imageName;
	}

	@Override
	public boolean validate() {
		return super.validate() && (imageName == null || imageName.isEmpty() || bufferedImage != null);
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		if (!validate()) {
			return new Rectangle((int) (myStyle.indent * scale), 0, 100, 100);
		}
		return new Rectangle((int) (myStyle.indent * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				((int) (myStyle.leading * scale)) + 
				(int) (bufferedImage.getHeight(observer) * scale));
	}

	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		if (!validate()) {
			g.setColor(Color.RED);
			g.drawRect(x, y, 100, 100);
			g.drawString("Image not available", x, y + 50);
			return;
		}
		int width = x + (int) (myStyle.indent * scale);
		int height = y + (int) (myStyle.leading * scale);
		g.drawImage(bufferedImage, width, height,
				(int)(bufferedImage.getWidth(observer)*scale),
				(int)(bufferedImage.getHeight(observer)*scale),
				observer);
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}
