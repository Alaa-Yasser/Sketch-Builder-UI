package main.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import main.controller.Main;
import main.controller.PaintFrameController;

import java.io.*;

public class PaintFrame extends Stage {

    private FXMLLoader loader;
    private Parent root;
    private Scene scene;

    public PaintFrame() {
        openStage();
    }

    public PaintFrame(File imageFile) {
        this();
        ((PaintFrameController) loader.getController()).setImage(imageFile);
    }

    private void openStage () {
        loader = new FXMLLoader(getClass().getResource("/resources/fxml/paintFrame.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene = new Scene(root, 700, 700);

        Main.maximizeWindow(this);

        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.setOnCloseRequest(event -> ((PaintFrameController) loader.getController()).close());
        this.show();
    }

    public void setParentFrame(Stage stage){
        ((PaintFrameController) loader.getController()).setGalleryFrame(stage);
    }

}