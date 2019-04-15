package sample;

import javafx.scene.canvas.*;
import javafx.scene.layout.StackPane;

public class DrawLine {

    GraphicsContext graphics;
    double from_x = 0;
    double from_y = 0;
    double to_x = 0;
    double to_y = 0;

    public DrawLine (Canvas canvas, StackPane stackPane){
        graphics = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            from_x = e.getSceneX();
            from_y = e.getSceneY();
        });

        canvas.setOnMouseDragged(e -> {
            stackPane.getChildren().remove(0);
            final Canvas temp_canvas = new Canvas(400, 400);
            final GraphicsContext gc = temp_canvas.getGraphicsContext2D();
            this.to_x = e.getSceneX();
            this.to_y = e.getSceneY();
            gc.strokeLine(from_x, from_y, to_x, to_y);
            stackPane.getChildren().add(0,temp_canvas);
        });

        canvas.setOnMouseReleased(e -> {
            to_x = e.getSceneX();
            to_y = e.getSceneY();
            graphics.strokeLine(from_x, from_y, to_x, to_y);;
        });

        stackPane.getChildren().addAll(new Canvas(), canvas);

    }

}
