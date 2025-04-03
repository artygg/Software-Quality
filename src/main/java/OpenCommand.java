import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpenCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public OpenCommand(Frame parent) {
        this.parent = parent;
        presentation = Presentation.getInstance();
    }

    @Override
    public void execute() {
        this.presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, "test.xml");
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }
}