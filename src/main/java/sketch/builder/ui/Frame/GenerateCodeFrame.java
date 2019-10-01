package sketch.builder.ui.Frame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;
import sketch.builder.ui.controller.GenerateCodeFrameController;
import sketch.builder.ui.controller.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GenerateCodeFrame extends Stage {

    private FXMLLoader loader;
    private Parent root;
    private Scene scene;

    public GenerateCodeFrame() {
        openStage();
    }

    public GenerateCodeFrame(File file) {
        this();
        ((GenerateCodeFrameController) loader.getController()).setImagePath(file);
    }

    public GenerateCodeFrame(ArrayList<File> files) {
        this();
        ((GenerateCodeFrameController) loader.getController()).setImagePath(files);
    }

    private void openStage (){
        try {

            loader = new FXMLLoader(getClass().getResource("/fxml/GenerateCodeFrame.fxml"));
            root = loader.load();
            scene = new Scene(root);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.setResizable(false);

        Main.openStage(this, 600, 308);
    }
}
