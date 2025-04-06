import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyController extends KeyAdapter
{
	private Map<Integer, Command> commandMap;

	public KeyController() {
		this(new ProductionAppController());
	}

	public KeyController(AppController appControllerForQuit) {
		commandMap = new HashMap<>();

		commandMap.put(KeyEvent.VK_PAGE_DOWN, new NextSlideCommand());
		commandMap.put(KeyEvent.VK_DOWN, new NextSlideCommand());
		commandMap.put(KeyEvent.VK_ENTER, new NextSlideCommand());
		commandMap.put(KeyEvent.VK_PAGE_UP, new PreviousSlideCommand());
		commandMap.put(KeyEvent.VK_UP, new PreviousSlideCommand());
		commandMap.put(KeyEvent.VK_Q, new ExitCommand(appControllerForQuit));
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		Command command = commandMap.get(keyEvent.getKeyCode());
		if (command != null) {
			command.execute();
		}
	}
}
