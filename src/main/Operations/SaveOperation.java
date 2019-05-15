package main.Operations;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.controller.DrawCanvas;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SaveOperation extends Operation {

    private Stage stage;

    public  void setStage (Stage stage){
        this.stage = stage;
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void operate() {
        WritableImage image = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
        canvas.snapshot(null, image);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File imageFile = fileChooser.showSaveDialog(stage);

        if (imageFile != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", imageFile);
            } catch (IOException s) {
                System.err.println("Couldn't save the file!");
            }
        }

            canvas.setIsEdited(false);
    }


}