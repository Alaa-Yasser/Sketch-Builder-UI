package sketch.builder.ui.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import sketch.builder.ui.controller.GalleryFrameController;
import sketch.builder.ui.controller.Main;

import java.io.IOException;

public class GalleryFrame extends Stage {

    private FXMLLoader loader;
    private Parent root;
    private Scene scene;

    public GalleryFrame() {
        try {
            loader = new FXMLLoader(getClass().getResource("/fxml/GalleryFrame.fxml"));
            root = loader.load();
            scene = new Scene(root, 500, 500);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Main.maximizeWindow(this);
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);

        this.setOnCloseRequest(event -> ((GalleryFrameController) loader.getController()).close());

    }
}

