package sketch.builder.ui.Tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.*;
import javafx.scene.layout.StackPane;
import sketch.builder.ui.controller.DrawCanvas;

public class Line extends Shape {
    private GraphicsContext graphics;
    private double endX;
    private double endY;
    private StackPane stackPane;

    @Override
    public  void setLayout (StackPane stackPane){
        this.stackPane = stackPane;
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            super.initX = e.getX();
            super.initY = e.getY();
        });

        canvas.setOnMouseDragged(e -> {
            stackPane.getChildren().remove(0);
            final DrawCanvas tempCanvas = new DrawCanvas();
            tempCanvas.widthProperty().bind(stackPane.widthProperty().subtract(10));
            tempCanvas.heightProperty().bind(stackPane.heightProperty().subtract(10));
            final GraphicsContext gc = tempCanvas.getGraphicsContext2D();
            this.endX = e.getX();
            this.endY = e.getY();
            gc.setLineWidth(super.LINE_WIDTH);
            gc.strokeLine(super.initX, super.initY, this.endX, this.endY);
            stackPane.getChildren().add(0, tempCanvas);
        });

        canvas.setOnMouseReleased(e -> {
            this.endX = e.getX();
            this.endY = e.getY();
            draw();
            canvas.setIsEdited(true);
        });

    }

    @Override
    public void setCursor() {
        canvas.setCursor(Cursor.CROSSHAIR);
    }

    @Override
    public void draw() {
        graphics.setLineWidth(super.LINE_WIDTH);
        graphics.strokeLine(super.initX, super.initY, this.endX, this.endY);
    }

}