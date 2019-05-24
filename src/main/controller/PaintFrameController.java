package main.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import main.Operations.ClearOperation;
import main.Operations.CloseOperation;
import main.Operations.OpenOperation;
import main.Operations.SaveOperation;

import main.Tools.Brush;
import main.Tools.Erase;

import java.io.*;

public class PaintFrameController {

    @FXML
    BorderPane mainLayout;
    @FXML
    Pane topBorder;
    @FXML
    Pane bottomBorder;
    @FXML
    Pane rightBorder;
    @FXML
    Pane leftBorder;
    @FXML
    HBox titleBar;
    @FXML
    FontIcon closeIcon;
    @FXML
    FontIcon maximizeIcon;
    @FXML
    FontIcon minusIcon;
    @FXML
    MenuItem openItem;
    @FXML
    MenuItem saveItem;
    @FXML
    MenuItem clearItem;
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
    @FXML
    StackPane canvasPane;

    private double xOffset;
    private double yOffset;

    private Stage stage;

    public void initialize() {

        canvasPane.setMinSize(300, 300);
        ResizeMod.makeResizable(canvasPane, null);

        canvasPane.widthProperty().addListener(evn -> {drawCanvas.setWidth(canvasPane.getWidth()-10);});
        canvasPane.heightProperty().addListener(evn->{drawCanvas.setHeight(canvasPane.getHeight() - 10);});

        titleBar.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            Stage stage = ((Stage)titleBar.getScene().getWindow());

            Main.maximizeWindow(stage);

            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        titleBar.setOnMouseClicked(event -> {
            Stage stage = (Stage) titleBar.getScene().getWindow();
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2)
                    Main.maximizeWindow(stage);
            }
        });


        closeIcon.setOnMousePressed(event -> close() );

        maximizeIcon.setOnMousePressed(event -> {
            Stage stage = (Stage) maximizeIcon.getScene().getWindow();
            Main.maximizeWindow(stage);
        });

        minusIcon.setOnMousePressed(event -> ((Stage) (minusIcon.getScene().getWindow())).setIconified(true));


        openItem.setOnAction(event -> openImage());

        saveItem.setOnAction(event -> {
            SaveOperation save = new SaveOperation();
            save.setStage((Stage) (mainLayout.getScene().getWindow()));
            save.setCanvas(drawCanvas);
            save.operate();
        });

        clearItem.setOnAction(event -> {
            ClearOperation clear = new ClearOperation();
            clear.setLayout(canvasPane);
            clear.setCanvas(drawCanvas);
            clear.operate();
        });

        closeItem.setOnAction(event -> {
            close();
        });

        brushItem.setOnAction(event -> setBrushItem() );

//        lineItem.setOnAction(event -> {
//            Line line = new Line();
//            line.setLayout(canvasPane);
//            line.setCanvas(drawCanvas);
//            line.setCursor();
//            line.draw();
//        });
//
//        rectItem.setOnAction(event -> {
//            RectangleShape rect = new RectangleShape();
//            rect.setLayout(canvasPane);
//            rect.setCanvas(drawCanvas);
//            rect.setCursor();
//            rect.draw();
//        });

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
        setBrushItem();
    }
    private void setBrushItem(){
        Brush brush = new Brush();
        brush.setCanvas(drawCanvas);
        try {
            brush.setCursor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        brush.draw();
    }

    public void close () {
        CloseOperation close = new CloseOperation();
        close.setStage((Stage) (mainLayout.getScene().getWindow()));
        close.setCanvas(drawCanvas);
        close.operate();
        stage.show();
    }

    public void openImage () {
        OpenOperation open = new OpenOperation();
        open.setStage((Stage) (mainLayout.getScene().getWindow()), this);
        open.setCanvas(drawCanvas);
        open.operate();
    }

    public void setImage (File imageFile) {
        OpenOperation open = new OpenOperation();
        open.setStage((Stage) (mainLayout.getScene().getWindow()), this);
        open.setCanvas(drawCanvas);
        open.setImage(imageFile);
    }
    public void setDrawCanvasSize(double w, double h){
        this.canvasPane.setPrefWidth(w);
        this.canvasPane.setPrefHeight(h);
        this.drawCanvas.setWidth(w-10);
        this.drawCanvas.setHeight(h-10);
    }

    public void setGalleryFrame (Stage stage) {
        this.stage = stage;
    }
}
