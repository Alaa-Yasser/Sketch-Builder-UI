package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Client client = new Client("127.0.0.1", 9999);
        MainFrame mainFrame = new MainFrame(client);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
