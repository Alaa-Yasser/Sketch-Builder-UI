package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Operations.OpenOperation;
import main.Tools.Brush;
import main.Tools.Erase;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.FileNotFoundException;

public class PaintFrameController {

    @FXML
    BorderPane mainLayout;
    @FXML
    BorderPane titleBar;
    @FXML
    FontIcon closeIcon;
    @FXML
    FontIcon minusIcon;
    @FXML
    MenuItem openItem;
    @FXML
    MenuItem closeItem;
    @FXML
    MenuItem brushItem;
    @FXML
    MenuItem eraseItem;
    @FXML
    StackPane stackPane;
    @FXML
    DrawCanvas drawCanvas;

    private double xOffset;
    private double yOffset;

    public void initialize() {

        drawCanvas.widthProperty().bind(stackPane.widthProperty());
        drawCanvas.heightProperty().bind(stackPane.heightProperty());

        titleBar.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        titleBar.setOnMouseDragged(e -> {
            (titleBar.getScene().getWindow()).setX(e.getScreenX() - xOffset);
            (titleBar.getScene().getWindow()).setY(e.getScreenY() - yOffset);
        });


        closeIcon.setOnMousePressed(event -> closeIcon.getScene().getWindow().hide());

        minusIcon.setOnMousePressed(event -> ((Stage) (minusIcon.getScene().getWindow())).setIconified(true));


        openItem.setOnAction(event -> {
            OpenOperation open = new OpenOperation();
            open.setStage((Stage) (mainLayout.getScene().getWindow()));
            open.setCanvas(drawCanvas);
            open.operate();
        });

        closeItem.setOnAction(event -> {
            Main.client.sendMessage("exit");
            closeIcon.getScene().getWindow().hide();
        });

        brushItem.setOnAction(event -> {
            Brush brush = new Brush();
            brush.setCanvas(drawCanvas);
            try {
                brush.setCursor();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            brush.draw();
        });

        eraseItem.setOnAction(event -> {
            Erase erase = new Erase();
            erase.setCanvas(drawCanvas);
            try {
                erase.setCursor();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            erase.draw();
        });
    }
}
