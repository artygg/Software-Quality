import javax.swing.*;
import java.awt.*;
import java.io.File;
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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a presentation file");

        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML Files", "xml"));

        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file path
            File selectedFile = fileChooser.getSelectedFile();
            try {
                presentation.clear();
                Accessor xmlAccessor = new XMLAccessor();
                xmlAccessor.loadFile(presentation, selectedFile.getAbsolutePath());
                presentation.setSlideNumber(0);
                parent.repaint();
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                        "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
