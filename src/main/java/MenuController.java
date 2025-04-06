import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {

	private Frame parent;

	private static final long serialVersionUID = 227L;

	public MenuController(Frame frame) {
		parent = frame;
		MenuItem menuItem;

		Menu fileMenu = new Menu("File");
		fileMenu.add(menuItem = mkMenuItem("Open"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new OpenCommand(parent).execute();
			}
		});
		fileMenu.add(menuItem = mkMenuItem("New"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new NewCommand(parent).execute();
			}
		});
		fileMenu.add(menuItem = mkMenuItem("Save"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new SaveCommand(parent).execute();
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem("Exit"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new ExitCommand().execute();
			}
		});
		add(fileMenu);

		Menu viewMenu = new Menu("View");
		viewMenu.add(menuItem = mkMenuItem("Next"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new NextSlideCommand().execute();
			}
		});
		viewMenu.add(menuItem = mkMenuItem("Prev"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new PreviousSlideCommand().execute();
			}
		});
		viewMenu.add(menuItem = mkMenuItem("Go to"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new GotoCommand().execute();
			}
		});
		add(viewMenu);

		Menu helpMenu = new Menu("Help");
		helpMenu.add(menuItem = mkMenuItem("About"));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
		setHelpMenu(helpMenu);
	}

	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
