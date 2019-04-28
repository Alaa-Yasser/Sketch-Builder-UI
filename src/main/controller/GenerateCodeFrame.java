package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;

public class GenerateCodeFrame extends Stage {

    private Scene scene;

    public GenerateCodeFrame() {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/GenerateCodeFrame.fxml"));
            scene = new Scene(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.setResizable(false);
        this.show();

    }

}
