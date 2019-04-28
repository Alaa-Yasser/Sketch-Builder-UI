package main.Tools;

import javafx.scene.canvas.GraphicsContext;
import main.controller.DrawCanvas;

public class Erase extends Tool {

    private GraphicsContext graphics;

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics= this.canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            graphics.beginPath();
            graphics.stroke();
        });
        canvas.setOnMouseDragged(e ->{
            super.initX = e.getX();
            super.initY = e.getY();
            draw();
            graphics.stroke();
        });
        canvas.setOnMouseReleased(e -> canvas.setIsEdited(true));
    }

    @Override
    public void draw() {
        graphics.clearRect(super.initX, super.initY, 15, 15);
    }
}
