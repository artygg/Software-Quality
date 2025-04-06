import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;


/** <p>SlideViewerComponent is a graphical component that can show slides.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {
		
	private Slide slide;
	private Font labelFont = null;
	private Presentation presentation = null;
	private JFrame frame = null;
	
	private static final long serialVersionUID = 227L;
	
	private static final Color BGCOLOR = Constants.DEFAULT_BACKGROUND_COLOR;
	private static final Color COLOR = Constants.DEFAULT_TEXT_COLOR;
	private static final String FONTNAME = Constants.DEFAULT_FONT_NAME;
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = Constants.DEFAULT_FONT_SiZE;
	private static final int XPOS = Constants.TITLE_COORD_X;
	private static final int YPOS = Constants.TITLE_COORD_Y;
	private SlideRenderer renderer = new DefaultRenderer();

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		setBackground(BGCOLOR); 
		presentation = pres;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		this.frame = frame;
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	public void update(Presentation presentation, Slide data) {
		if (data == null) {
			repaint();
			return;
		}
		this.presentation = presentation;
		this.slide = data;
		repaint();
		frame.setTitle(presentation.getTitle());
	}

	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presentation.getCurrentSlideNumber() < 0 || slide == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presentation.getCurrentSlideNumber()) + " of " +
                 presentation.getSize(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		renderer.render(slide, g, area, this);
	}

	public void setRenderer(SlideRenderer r)
	{
		this.renderer = r;
	}
}
