import javax.swing.*;

public class GotoCommand implements Command {
    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog("Page number?");
        try {
            int pageNumber = Integer.parseInt(pageNumberStr);
            int totalSlides = Presentation.getInstance().getSize();
            if (pageNumber >= 1 && pageNumber <= totalSlides) {
                Presentation.getInstance().setSlideNumber(pageNumber - 1);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid page number! Please enter a number between 1 and " + totalSlides + ".",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
