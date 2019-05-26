package main.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.controller.Main;
import main.controller.OpenImageFrameController;

import java.io.File;
import java.io.IOException;

public class AboutFrame extends Stage {

    FXMLLoader loader;
    Parent root;
    Scene scene;

    public AboutFrame () {

        try {
            loader = new FXMLLoader(getClass().getResource("/resources/fxml/AboutFrame.fxml"));
            root = loader.load();


        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);

        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.show();
        Main.openStage(this, 600, 589);

    }
}
