package sketch.builder.ui.controller;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sketch.builder.ui.Frame.GalleryFrame;
import sketch.builder.ui.Frame.LoadFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Main extends Application {
    public static Client client;
    public static LoadFrame loadFrame;
    public static  HostServices services;
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadFrame = new LoadFrame();
        GalleryFrame galleryFrame = new GalleryFrame();
        galleryFrame.show();
        services = getHostServices();

    }

    public static void main(String[] args) {
        File file = new File(".config/port.config");
        int port = 1996;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            port = Integer.parseInt(br.readLine());
        }catch (Exception ex){
            ex.printStackTrace();
        }

        client = new Client("127.0.0.1", port);

//        client.connect();
        launch(args);
//        client.disconnect();
    }

    public static void maximizeWindow (Stage stage) {
        final Screen primaryScreen = Screen.getPrimary();
        final List<Screen> allScreens = Screen.getScreens();
        Screen secondaryScreen;
        if (allScreens.size() <= 1) {
            System.out.println("Only one screen");
            secondaryScreen = primaryScreen;
        } else {
            // UPDATED:
            if (allScreens.get(0) == (primaryScreen)) {
                secondaryScreen = allScreens.get(1);
            } else {
                secondaryScreen = allScreens.get(0);
            }
        }
        Screen screen = secondaryScreen;

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

    public static void openStage (Stage stage, double w, double h) {
        Screen screen = Screen.getScreens().get(Screen.getScreens().size() -1);
        Rectangle2D rect = screen.getVisualBounds();

        stage.setWidth(w);
        stage.setHeight(h);
        stage.setX(rect.getWidth()/2 - stage.getWidth()/2);
        stage.setY(rect.getHeight()/2  - stage.getHeight()/2);
    }


}
