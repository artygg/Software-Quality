public class ExitCommand implements Command {
    private final AppController appController;

    public ExitCommand() {
        this(new ProductionAppController());
    }

    public ExitCommand(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void execute() {
        appController.shutdown();
    }
}