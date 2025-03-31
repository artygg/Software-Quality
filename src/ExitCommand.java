public class ExitCommand implements Command {
    @Override
    public void execute() {
        Presentation.getInstance().exit(0);
    }
}