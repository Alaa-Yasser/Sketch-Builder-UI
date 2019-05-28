package main.Operations;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import main.controller.DrawCanvas;

import java.util.Optional;

public class CloseOperation extends Operation {

    private Stage stage;
    private Stage openStage;

    public  void setStage (Stage stage){
            this.stage = stage;
        }

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void operate() {
        if (!canvas.getIsEdited()){
            closeStage();
        }
        else
        {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("The current image is not saved. Save it?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    try {
                        SaveOperation save = new SaveOperation();
                        save.setStage(stage);
                        save.setCanvas(canvas);
                        save.saveAS();
                        closeStage();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else if (result.get() == ButtonType.NO){
                    closeStage();
                }
                else if (result.get() == ButtonType.CANCEL){
                    alert.hide();
                }
        }
    }

    public void setOpenStage (Stage openStage) {
        this.openStage = openStage;
    }

    private void closeStage(){
        System.out.println("closeStage");
        if(this.openStage != null)
            openStage.show();
        stage.close();
    }
}
