public class ProductionAppController implements AppController {
    @Override
    public void shutdown() {
        System.exit(0); // production only
    }
}