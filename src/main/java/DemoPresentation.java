/** A built in demo-presentation
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename) {
		presentation.setTitle("Demo Presentation");
		Slide slide;
		slide = new Slide();
		slide.setTitle("JabberPoint");
		slide.appendText(1, "The Java Presentation Tool");
		slide.appendText(2, "Copyright (c) 1996-2000: Ian Darwin");
		slide.appendText(2, "Copyright (c) 2000-now:");
		slide.appendText(2, "Gert Florijn andn Sylvia Stuurman");
		slide.appendText(4, "Starting JabberPoint without a filename");
		slide.appendText(4, "shows this presentation");
		slide.appendText(1, "Navigate:");
		slide.appendText(3, "Next slide: PgDn or Enter");
		slide.appendText(3, "Previous slide: PgUp or up-arrow");
		slide.appendText(3, "Quit: q or Q");
		presentation.addSlide(slide);

		slide = new Slide();
		slide.setTitle("Demonstration of levels and stijlen");
		slide.appendText(1, "Level 1");
		slide.appendText(2, "Level 2");
		slide.appendText(1, "Again level 1");
		slide.appendText(1, "Level 1 has style number 1");
		slide.appendText(2, "Level 2 has style number  2");
		slide.appendText(3, "This is how level 3 looks like");
		slide.appendText(4, "And this is level 4");
		presentation.addSlide(slide);

		slide = new Slide();
		slide.setTitle("The third slide");
		slide.appendText(1, "To open a new presentation,");
		slide.appendText(2, "use File->Open from the menu.");
		slide.appendText(1, " ");
		slide.appendText(1, "This is the end of the presentation.");
		slide.appendItem("image", 1, "JabberPoint.gif");
		presentation.addSlide(slide);
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		ErrorDisplay.showError("Save As->Demo! called", "Demo Presentation");
	}
}
