package main.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import main.controller.OpenImageFrameController;

import java.io.*;

public class OpenImageFrame extends Stage {

    private FXMLLoader loader;
    private Parent root = null;
    private Scene scene;
    private File imageFile;
    private double sceneWidth;
    private double sceneHeight;

    public OpenImageFrame (File imageFile) {

        this.imageFile = imageFile;

        try {
            loader = new FXMLLoader (getClass().getResource("/resources/fxml/OpenImageFrame.fxml"));
            root = loader.load();


        } catch (IOException e) {
            e.printStackTrace();
        }
        ((OpenImageFrameController)loader.getController()).setImageFile(imageFile);
        sceneWidth = ((OpenImageFrameController)loader.getController()).getImageWidth();
        sceneHeight = ((OpenImageFrameController)loader.getController()).getImageHeight();

        scene = new Scene(root, 700, 700);

        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        //this.setMaximized(true);
        this.show();


    }

}