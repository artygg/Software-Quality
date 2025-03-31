public class NextSlideCommand implements Command {
    @Override
    public void execute() {
        Presentation.getInstance().nextSlide();
    }
}