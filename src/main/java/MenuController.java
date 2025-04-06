public class MenuController extends MenuBar {

	private Frame parent;

	public MenuController(Frame frame) {
		this(frame, new OpenCommand(frame)); // Production default
	}

	// NEW constructor for injecting a custom OpenCommand
	public MenuController(Frame frame, OpenCommand customOpenCommand) {
		parent = frame;
    
		MenuItem menuItem;

		Menu fileMenu = new Menu("File");

		// "Open" uses your injected command
		fileMenu.add(menuItem = mkMenuItem("Open"));
		menuItem.addActionListener(e -> customOpenCommand.execute());

		fileMenu.add(menuItem = mkMenuItem("New"));
		menuItem.addActionListener(e -> new NewCommand(parent).execute());

		fileMenu.add(menuItem = mkMenuItem("Save"));
		menuItem.addActionListener(e -> new SaveCommand(parent).execute());

		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem("Exit"));
		menuItem.addActionListener(e -> new ExitCommand().execute());
		add(fileMenu);

		Menu viewMenu = new Menu("View");
		viewMenu.add(menuItem = mkMenuItem("Next"));
		menuItem.addActionListener(e -> new NextSlideCommand().execute());

		viewMenu.add(menuItem = mkMenuItem("Prev"));
		menuItem.addActionListener(e -> new PreviousSlideCommand().execute());

		viewMenu.add(menuItem = mkMenuItem("Go to"));
		menuItem.addActionListener(e -> new GotoCommand().execute());
		add(viewMenu);

		Menu helpMenu = new Menu("Help");
		helpMenu.add(menuItem = mkMenuItem("About"));
		menuItem.addActionListener(e -> AboutBox.show(parent));
		setHelpMenu(helpMenu);
	}

	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
