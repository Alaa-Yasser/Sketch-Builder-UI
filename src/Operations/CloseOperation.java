package Operations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import sample.DrawCanvas;

import java.util.Optional;

public class CloseOperation extends Operation {

    private GraphicsContext graphics;

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();
    }

    @Override
    public void operate() {
        if (!canvas.getIsEdited()){
            System.exit(0);
        }
        else
        {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("The current image is not saved. Save it?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    try {
                        SaveOperation save = new SaveOperation();
                        save.setCanvas(canvas);
                        save.operate();
                        System.exit(0);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else if (result.get() == ButtonType.NO){
                    System.exit(0);
                }
                else if (result.get() == ButtonType.CANCEL){
                    alert.hide();
                }
        }
    }
}
