package sample;

import Operations.CloseOperation;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TitleBar extends BorderPane {

    private HBox buttons ;
    private Button bClose;
    private Button bMinimize;
    private Text title;
    private CloseOperation close;
    private DrawCanvas canvas;

    public TitleBar (DrawCanvas canvas){
        this.canvas = canvas;

        this.setStyle("-fx-background-color: DarkBlue; ");

        buttons = new HBox();

        title = new Text("Paint Brush");
        title.setStyle("-fx-font-size: 20px;");
        title.setFill(Color.WHITE);

        bClose = new Button("x");
        bClose.setOnAction(e -> {
            e.consume();
            close = new CloseOperation();
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