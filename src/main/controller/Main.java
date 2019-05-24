package main.controller;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.stage.Screen;
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

    public static void maximizeWindow (Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D rect = screen.getVisualBounds();
        if (stage.isMaximized()){
            stage.setWidth(700);
            stage.setHeight(700);
            stage.setX(rect.getMaxX()/2 - stage.getWidth()/2);
            stage.setY(rect.getMaxY()/2  - stage.getHeight()/2);
//            topBorder.setCursor(Cursor.V_RESIZE);
//            bottomBorder.setCursor(Cursor.V_RESIZE);
//            leftBorder.setCursor(Cursor.H_RESIZE);
//            rightBorder.setCursor(Cursor.H_RESIZE);
            stage.setMaximized(false);
        }else {
            stage.setWidth(rect.getWidth());
            stage.setHeight(rect.getHeight());
            stage.setX(rect.getMinX());
            stage.setY(rect.getMinY());
//            topBorder.setCursor(Cursor.DEFAULT);
//            bottomBorder.setCursor(Cursor.DEFAULT);
//            leftBorder.setCursor(Cursor.DEFAULT);
//            rightBorder.setCursor(Cursor.DEFAULT);
            stage.setMaximized(true);
        }
    }


}
