package main.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;

public class LoadFrame extends Stage {

    private Parent root;
    private Scene scene;

    public LoadFrame () {

        try {
            root = FXMLLoader.load(getClass().getResource("/resources/fxml/LoadFrame.fxml"));
            scene = new Scene(root, 600, 330);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        this.setResizable(false);
    }

    public void showStage () {
        this.show();
    }

    public void hideStage () {
        this.hide();
    }
}
