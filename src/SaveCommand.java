import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SaveCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public SaveCommand(Frame parent) {
        this.presentation = Presentation.getInstance();
        this.parent = parent;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, "dump.xml");
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}