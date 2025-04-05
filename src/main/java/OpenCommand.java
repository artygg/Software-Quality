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
        // Open file chooser to select the XML file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a presentation file");

        // Set the file filter to only show XML files
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML Files", "xml"));

        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file path
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Clear current presentation and load the new one from the selected file
                presentation.clear();
                Accessor xmlAccessor = new XMLAccessor();
                xmlAccessor.loadFile(presentation, selectedFile.getAbsolutePath());
                presentation.setSlideNumber(0); // Set the first slide as the current slide
                parent.repaint(); // Repaint the frame to display the new content
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                        "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
