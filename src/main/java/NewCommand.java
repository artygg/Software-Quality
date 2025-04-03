import java.awt.*;

public class NewCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public NewCommand(Frame parent) {
        this.presentation = Presentation.getInstance();;
        this.parent = parent;
    }

    @Override
    public void execute() {
        presentation.clear();
        parent.repaint();
    }
}