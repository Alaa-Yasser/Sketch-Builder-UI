package sketch.builder.ui.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sketch.builder.ui.controller.Main;

import java.io.IOException;

public class AboutFrame extends Stage {

    FXMLLoader loader;
    Parent root;
    Scene scene;

    public AboutFrame () {

        try {
            loader = new FXMLLoader(getClass().getResource("/fxml/AboutFrame.fxml"));
            root = loader.load();


        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root);

        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        Main.openStage(this, 603, 504);

    }
}
