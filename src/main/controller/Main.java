package main.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Client client;
    public static LoadFrame loadFrame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadFrame = new LoadFrame();
        GalleryFrame galleryFrame = new GalleryFrame();

    }

    public static void main(String[] args) {
        client = new Client("127.0.0.1", 1996);

//        client.connect();
        launch(args);
//        client.disconnect();
    }

}
