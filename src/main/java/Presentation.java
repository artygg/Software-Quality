import java.util.ArrayList;


/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation implements SlideManager, NavigationController {
	private String showTitle; // title of the presentation
	private ArrayList<Slide> showList = null; // an ArrayList with Slides
	private int currentSlideNumber = 0; // the slidenummer of the current Slide
	private SlideViewerComponent slideViewComponent = null; // the viewcomponent of the Slides
	private static volatile Presentation instance;

	private Presentation() {
		slideViewComponent = null;
		clear();
	}

	public static Presentation getInstance() {
		if (instance == null) {
			synchronized (Presentation.class) {
				if (instance == null) {
					instance = new Presentation();
				}
			}
		}
		return instance;
	}

	// SlideManager implementation
	@Override
	public void addSlide(Slide slide) {
		showList.add(slide);
		if (currentSlideNumber < 0 && getSize() == 1) {
			setSlideNumber(0);
		}
	}

	@Override
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()) {
			return null;
		}
		return showList.get(number);
	}

	@Override
	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}

	@Override
	public int getSize() {
		return showList.size();
	}

	@Override
	public void clear() {
		showList = new ArrayList<>();
		setSlideNumber(-1);
		setTitle("");
	}

	@Override
	public String getTitle() {
		return showTitle;
	}

	@Override
	public void setTitle(String title) {
		this.showTitle = title;
	}

	// NavigationController implementation
	@Override
	public void nextSlide() {
		if (currentSlideNumber < (showList.size() - 1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	@Override
	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
		}
	}

	@Override
	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		if (slideViewComponent != null) {
			slideViewComponent.update(this, getCurrentSlide());
		}
	}

	@Override
	public int getCurrentSlideNumber() {
		return currentSlideNumber;
	}

	// UI-related methods
	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public void exit(int n) {
		System.exit(n);
	}
}
