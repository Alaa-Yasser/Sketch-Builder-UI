package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainFrame mainFrame = new MainFrame(client);

    }

    public static void main(String[] args) {
        client = new Client("127.0.0.1", 1996);
        client.connect();
        launch(args);
        client.disconnect();
    }

}
