package main.controller;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Frame.GalleryFrame;
import main.Frame.LoadFrame;

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

    public static void maximizeWindow (Stage stage) {
        Screen screen = Screen.getScreens().get(Screen.getScreens().size() -1);
        Rectangle2D rect = screen.getVisualBounds();
        if (stage.getWidth() == rect.getWidth()){
            stage.setWidth(500);
            stage.setHeight(500);
            stage.setX(rect.getWidth()/2 - stage.getWidth()/2);
            stage.setY(rect.getHeight()/2  - stage.getHeight()/2);

//            topBorder.setCursor(Cursor.V_RESIZE);
//            bottomBorder.setCursor(Cursor.V_RESIZE);
//            leftBorder.setCursor(Cursor.H_RESIZE);
//            rightBorder.setCursor(Cursor.H_RESIZE);
//            stage.setMaximized(false);
        }else {
            stage.setX(rect.getMinX());
            stage.setY(rect.getMinY());
            stage.setWidth(rect.getWidth());
            stage.setHeight(rect.getHeight());

//            topBorder.setCursor(Cursor.DEFAULT);
//            bottomBorder.setCursor(Cursor.DEFAULT);
//            leftBorder.setCursor(Cursor.DEFAULT);
//            rightBorder.setCursor(Cursor.DEFAULT);
//            stage.setMaximized(true);
        }
    }


}
