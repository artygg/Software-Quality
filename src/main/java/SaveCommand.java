import javax.swing.*;
import java.awt.*;
import java.io.File;
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

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Presentation");

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int userSelection = fileChooser.showSaveDialog(parent);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".xml")) {
                filePath += ".xml";
            }

            Accessor xmlAccessor = new XMLAccessor();
            try {
                xmlAccessor.saveFile(presentation, filePath);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
