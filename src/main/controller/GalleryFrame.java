package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;

public class GalleryFrame extends Stage {

    private FXMLLoader loader;
    private Parent root;
    private Scene scene;

    public GalleryFrame() {
        try {

            loader = new FXMLLoader(getClass().getResource("/resources/fxml/GalleryFrame.fxml"));
            root = loader.load();
            scene = new Scene(root, 500, 500);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.setMaximized(true);
        this.show();

        this.setOnCloseRequest(event -> ((GalleryFrameController) loader.getController()).close());

    }
}

