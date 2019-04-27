package main.Tools;

import javafx.scene.canvas.*;
import main.sample.DrawCanvas;


public class Brush extends Tool {
    private GraphicsContext graphics;

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            graphics.beginPath();
            super.initX = e.getX();
            super.initY = e.getY();
            draw();
            graphics.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            super.initX = e.getX();
            super.initY = e.getY();
            draw();
            graphics.stroke();
        });
        canvas.setOnMouseReleased(e -> canvas.setIsEdited(true));
    }

    @Override
    public void draw() {
        graphics.setLineWidth(super.LINE_WIDTH);
        graphics.lineTo(super.initX, super.initY);
    }

}
