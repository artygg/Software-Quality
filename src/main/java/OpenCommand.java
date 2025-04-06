import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenCommand implements Command {
    private final Presentation presentation;
    private final Frame parent;
    private final FileOpenController fileOpenController;

    public OpenCommand(Frame parent) {
        this(parent, new ProductionFileOpenController());
    }

    public OpenCommand(Frame parent, FileOpenController fileOpenController) {
        this.parent = parent;
        this.fileOpenController = fileOpenController;
        this.presentation = Presentation.getInstance();
    }

    @Override
    public void execute() {
        File selectedFile = fileOpenController.chooseFileToOpen(parent);
        if (selectedFile != null) {
            try {
                presentation.clear();
                Accessor xmlAccessor = new XMLAccessor();
                xmlAccessor.loadFile(presentation, selectedFile.getAbsolutePath());
                presentation.setSlideNumber(0);
                parent.repaint();
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(
                        parent,
                        "IO Exception: " + exc,
                        "Load Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
