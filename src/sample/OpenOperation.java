package sample;

import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;

import java.io.File;
import java.util.Optional;

public class OpenOperation extends Operation {

    private GraphicsContext graphics;
    private Stage stage;

    public  void setStage (Stage stage){
        this.stage = stage;
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();
    }

    @Override
    public void operate() {
        if (!canvas.getIsEdited()){
           open();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("The current image is not saved. SaveOperation it?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    try {
                        SaveOperation s = new SaveOperation();
                        s.setCanvas(canvas);
                        s.operate();
                        open();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else if (result.get() == ButtonType.NO){
                    open();
                }
        }
    }

    private void open (){
        final FileChooser f = new FileChooser();
        File file = f.showOpenDialog(stage);
        if (file != null) { // only proceed, if file was chosen
            //  MainFrame openFrame = new MainFrame();
            Image img = new Image(file.toURI().toString());
            graphics.drawImage(img, 0, 0);
            // openFrame.drawArea.getGraphicsContext2D().drawImage(img, 0, 0);
        }
    }
}
