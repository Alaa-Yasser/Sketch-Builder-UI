package sketch.builder.ui.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import sketch.builder.ui.controller.Main;

import java.io.IOException;

public class LoadFrame extends Stage {

    private Parent root;
    private Scene scene;

    public LoadFrame () {

        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/LoadFrame.fxml"));
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        this.setResizable(false);
    }

    public void showStage () {
        if(!this.isShowing()){
            this.show();
            Main.openStage(this, 600, 308);
        }

    }
}
