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
        // Create a file chooser for saving files
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Presentation");

        // Set the initial directory to the user's default directory
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        // Show the "Save As" dialog and get the user's selection
        int userSelection = fileChooser.showSaveDialog(parent);

        // If the user chooses a file (i.e., doesn't cancel)
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File fileToSave = fileChooser.getSelectedFile();

            // Ensure the file has the correct extension (e.g., .xml)
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".xml")) {
                filePath += ".xml";
            }

            // Create an XMLAccessor to save the file
            Accessor xmlAccessor = new XMLAccessor();
            try {
                // Save the presentation to the selected file
                xmlAccessor.saveFile(presentation, filePath);
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
