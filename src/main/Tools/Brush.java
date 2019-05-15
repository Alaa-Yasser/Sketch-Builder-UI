package main.Tools;

import javafx.scene.ImageCursor;
import javafx.scene.canvas.*;
import javafx.scene.image.Image;
import main.controller.DrawCanvas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


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
    public void setCursor() throws FileNotFoundException {
        FileInputStream cursorImgStream = new FileInputStream("icons/icons8-paint-filled-100.png");
        Image cursorImage = new Image(cursorImgStream);
        ImageCursor imageCursor = new ImageCursor(cursorImage);
        canvas.setCursor(imageCursor);
    }

    @Override
    public void draw() {
        graphics.setLineWidth(super.LINE_WIDTH);
        graphics.lineTo(super.initX, super.initY);
    }

}
