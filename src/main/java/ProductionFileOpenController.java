import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProductionFileOpenController implements FileOpenController {

    @Override
    public File chooseFileToOpen(Frame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a presentation file");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML Files", "xml"));

        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;  // user cancelled
    }
}
