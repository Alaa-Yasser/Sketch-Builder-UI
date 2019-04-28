package main.controller;

import main.Operations.CloseOperation;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TitleBar extends BorderPane {

    private HBox buttons ;
    private Button bClose;
    private Button bMinimize;
    private Text title;
    private CloseOperation close;
    private DrawCanvas canvas;

    public TitleBar (DrawCanvas canvas, Stage stage){
        this.canvas = canvas;

        buttons = new HBox();

        title = new Text("Paint Brush");
        title.setStyle("-fx-font-size: 20px;");

        bClose = new Button("x");
        bClose.setOnAction(e -> {
            e.consume();
            close = new CloseOperation();
            close.setStage(stage);
            close.setCanvas(canvas);
            close.operate();
        });

        bMinimize = new Button("-");
        bMinimize.setOnAction(e -> ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true));

        buttons.getChildren().addAll(bMinimize, bClose);
        this.setCenter(title);
        this.setRight(buttons);
    }
}