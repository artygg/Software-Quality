public class PreviousSlideCommand implements Command {
    @Override
    public void execute() {
        Presentation.getInstance().prevSlide();
    }
}