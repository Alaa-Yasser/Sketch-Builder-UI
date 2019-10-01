package sketch.builder.ui.Operations;

import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.*;
import sketch.builder.ui.controller.DrawCanvas;
import sketch.builder.ui.controller.PaintFrameController;

import java.io.File;
import java.util.Optional;

public class OpenOperation extends Operation {

    private GraphicsContext graphics;
    private Stage stage;
    private PaintFrameController paintController;
    private File imageFile ;

    public  void setStage (Stage stage, PaintFrameController paintController){
        this.stage = stage;
        this.paintController = paintController;
    }


    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void operate() {
        if (!canvas.getIsEdited()){
           open();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("The current image is not saved. Save it?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    try {
                        SaveOperation s = new SaveOperation();
                        s.setStage(stage);
                        s.setCanvas(canvas);
                        s.saveAS();
                        open();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else if (result.get() == ButtonType.NO){
                    open();
                }
                else if (result.get() == ButtonType.CANCEL){
                    alert.close();
                }
        }
    }

    private void open (){
        final FileChooser FILE_CHOOSER = new FileChooser();
        imageFile = FILE_CHOOSER.showOpenDialog(stage);
        canvas.setImageFile(imageFile);
        setImage(imageFile);
    }

    public void setImage (File imageFile) {
        if (imageFile != null)
        {
            Image img = new Image(imageFile.toURI().toString() );
            paintController.setDrawCanvasSize(img.getWidth(), img.getHeight());
            graphics = this.canvas.getGraphicsContext2D();
            graphics.drawImage(img, 0, 0);
            canvas.setIsOpened(true);
        }
    }

}
