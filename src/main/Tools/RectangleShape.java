package main.Tools;

import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import main.controller.DrawCanvas;

public class RectangleShape extends Shape {

    private StackPane stackPane;
    private GraphicsContext graphics;
    private Rectangle rect;
    private Rectangle rectChange;

    public RectangleShape () {
        rect = new Rectangle();
        rectChange = new Rectangle();
    }

    @Override
    public void setLayout(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();

        canvas.setCursor(Cursor.CROSSHAIR);

        canvas.setOnMousePressed(e -> {
            rect.setX(e.getX());
            rect.setY(e.getY());
            rectChange.setX(rect.getX());
            rectChange.setY(rect.getY());
        });

        canvas.setOnMouseDragged(e -> {
            stackPane.getChildren().remove(0);
            final DrawCanvas tempCanvas = new DrawCanvas();
            final GraphicsContext gc = tempCanvas.getGraphicsContext2D();
            tempCanvas.widthProperty().bind(stackPane.widthProperty());
            tempCanvas.heightProperty().bind(stackPane.heightProperty());

            rectChange.setWidth(Math.abs((e.getX() - rectChange.getX())));
            rectChange.setHeight(Math.abs((e.getY() - rectChange.getY())));

            if(rectChange.getX() > e.getX()) {
                rect.setX(e.getX());

            }

            if(rectChange.getY() > e.getY()) {
                rect.setY(e.getY());
            }

            gc.setLineWidth(super.LINE_WIDTH);
            gc.strokeRect(rect.getX(), rect.getY(), rectChange.getWidth(), rectChange.getHeight());
            stackPane.getChildren().add(0, tempCanvas);
        });

        canvas.setOnMouseReleased(e -> {
            draw();
            rect.setWidth(0);
            rect.setHeight(0);
            rectChange.setWidth(0);
            rectChange.setHeight(0);
            canvas.setIsEdited(true);
        });
    }

    @Override
    public void draw() {
        graphics.setLineWidth(super.LINE_WIDTH);
        graphics.strokeRect(rect.getX(), rect.getY(), rectChange.getWidth(), rectChange.getHeight());
    }
}
