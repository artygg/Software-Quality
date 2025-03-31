import javax.swing.*;

public class GotoCommand implements Command {
    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog("Page number?");
        int pageNumber = Integer.parseInt(pageNumberStr);
        Presentation.getInstance().setSlideNumber(pageNumber - 1);
    }
}