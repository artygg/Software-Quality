import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.Map;

/** <p>This is the KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter {
	private Presentation presentation;
	private Map<Integer, Command> commandMap;
	public KeyController() {
		commandMap = new HashMap<>();
		presentation = Presentation.getInstance();


		commandMap.put(KeyEvent.VK_PAGE_DOWN, new NextSlideCommand());
		commandMap.put(KeyEvent.VK_DOWN, new NextSlideCommand());
		commandMap.put(KeyEvent.VK_ENTER, new NextSlideCommand());
		commandMap.put(KeyEvent.VK_PAGE_UP, new PreviousSlideCommand());
		commandMap.put(KeyEvent.VK_UP, new PreviousSlideCommand());
		commandMap.put(KeyEvent.VK_Q, new ExitCommand());
	}

	public void keyPressed(KeyEvent keyEvent) {
		Command command = commandMap.get(keyEvent.getKeyCode());

		if (command != null) {
			command.execute();
		}
	}
}
