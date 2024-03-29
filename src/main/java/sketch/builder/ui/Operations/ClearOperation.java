package sketch.builder.ui.Operations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import sketch.builder.ui.controller.DrawCanvas;

public class ClearOperation extends Operation {

    private GraphicsContext graphics;
    private StackPane stackPane;

    public void setLayout (StackPane stackPane){
        this.stackPane = stackPane;
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();
    }

    @Override
    public void operate() {
//        stackPane.getChildren().remove(0);
//        stackPane.getChildren().add(0, new DrawCanvas());
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.setIsEdited(false);

    }
}
