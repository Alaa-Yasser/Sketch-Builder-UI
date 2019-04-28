package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class GalleryFrameController {

    @FXML
    BorderPane titleBar;
    @FXML
    FontIcon closeIcon;
    @FXML
    FontIcon minusIcon;
    @FXML
    MenuItem drawImageItem;
    @FXML
    MenuItem convertItem;
    @FXML
    MenuItem closeItem;

    private double xOffset;
    private double yOffset;

    public void initialize() {

        titleBar.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        titleBar.setOnMouseDragged(e -> {
            (titleBar.getScene().getWindow()).setX(e.getScreenX() - xOffset);
            (titleBar.getScene().getWindow()).setY(e.getScreenY() - yOffset);
        });


        closeIcon.setOnMousePressed(event -> {
            Main.client.sendMessage("exit");
            closeIcon.getScene().getWindow().hide();
        });

        minusIcon.setOnMousePressed(event -> ((Stage)(minusIcon.getScene().getWindow())).setIconified(true));


        drawImageItem.setOnAction(event -> {
            try {
                new PaintFrame();
            } catch (Exception e) { e.printStackTrace(); }
        });


        convertItem.setOnAction(event -> new GenerateCodeFrame());

        closeItem.setOnAction(event -> {
            Main.client.sendMessage("exit");
            closeIcon.getScene().getWindow().hide();
        });
        
    }

}

