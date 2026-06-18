import ui.LoginFrame;
import util.DatabaseInitializer;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        new LoginFrame();
    }
}