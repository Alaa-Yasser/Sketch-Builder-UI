package sample;

import javafx.scene.canvas.GraphicsContext;

public class ClearOperation extends Operation {

    private GraphicsContext graphics;

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();
    }

    @Override
    public void operate() {
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
