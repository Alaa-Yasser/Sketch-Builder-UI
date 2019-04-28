package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GalleryFrame extends Stage {

    private Scene scene;

    public GalleryFrame() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/GalleryFrame.fxml"));
            scene = new Scene(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.setResizable(false);
        this.show();

        this.setOnCloseRequest(event -> {
            Main.client.sendMessage("exit");
            System.exit(0);
        });


    }

}

