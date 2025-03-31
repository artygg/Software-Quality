import java.awt.*;

public class NewCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public NewCommand(Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        presentation.clear();
        parent.repaint();
    }
}