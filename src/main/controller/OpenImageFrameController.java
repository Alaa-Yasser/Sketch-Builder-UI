package main.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class OpenImageFrameController {

    @FXML
    Pane topBorder;
    @FXML
    Pane bottomBorder;
    @FXML
    Pane rightBorder;
    @FXML
    Pane leftBorder;
    @FXML
    ScrollPane scrollPane;
    @FXML
    HBox titleBar;
    @FXML
    FontIcon closeIcon;
    @FXML
    FontIcon maximizeIcon;
    @FXML
    FontIcon minusIcon;
    @FXML
    Text title;
    @FXML
    StackPane imageStackPane;

    private double xOffset;
    private double yOffset;

    private ImageView imageView;
    private BufferedImage openedImageBuffer;
    private Image openedImage;
    private File imageFile;

    private boolean isDragging = false;

    public void initialize() {

        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            isDragging = true;
        });

        titleBar.setOnMouseDragged(event -> {
            Stage stage = ((Stage)titleBar.getScene().getWindow());

            if(isDragging)
                Main.maximizeWindow(stage);

            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
            isDragging = false;
        });

        titleBar.setOnMouseClicked(event -> {
            Stage stage = (Stage) titleBar.getScene().getWindow();
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2)
                    Main.maximizeWindow(stage);
            }
        });

        closeIcon.setOnMousePressed(event -> {
            closeIcon.getScene().getWindow().hide();
        });
        maximizeIcon.setOnMousePressed(event -> {
            Stage stage = (Stage) maximizeIcon.getScene().getWindow();
            Main.maximizeWindow(stage);
        });

        minusIcon.setOnMousePressed(event -> ((Stage)(minusIcon.getScene().getWindow())).setIconified(true));

    }

    public void setImageFile (File imageFile) {
        this.imageFile = imageFile;

        title.setText(imageFile.getName());

        try {
            imageView = new ImageView();
            openedImage = new Image(new FileInputStream(imageFile));
            openedImageBuffer = ImageIO.read(imageFile);


            if (openedImage.getWidth() > 600)
                imageView.setFitWidth(openedImage.getWidth() * (0.3));
            else
                imageView.setFitWidth(openedImage.getWidth());

            if (openedImage.getHeight() > 600)
                imageView.setFitHeight(openedImage.getHeight() * (0.3));
            else
                imageView.setFitHeight(openedImage.getHeight());

            imageView.setImage(openedImage);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

            imageStackPane.getChildren().add(imageView);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public double getImageWidth () {return openedImageBuffer.getWidth();}
    public double getImageHeight () {return openedImageBuffer.getHeight();}

}
