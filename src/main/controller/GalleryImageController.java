package main.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;


public class GalleryImageController {

    private File imageFile;
    @FXML
    ImageView imageView;
    @FXML
    StackPane glass;

    public void initialize () {

    }

    public void setImageView (File imageFile) {
        try {
            this.imageFile = imageFile;
            final Image IMAGE = new Image(new FileInputStream(imageFile), 136, 0, true, true);
            this.imageView.setImage(IMAGE);
            imageView.setImage(IMAGE);
            imageView.setFitWidth(136);
            imageView.setFitHeight(145);
            Rectangle clip = new Rectangle();
            clip.setWidth(136);
            clip.setHeight(145);
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            clip.setStroke(Color.BLACK);
            imageView.setClip(clip);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void select () {glass.setStyle("-fx-background-color: rgba(0, 0, 100, 0.5);");}

    public void deselect () {glass.setStyle("-fx-background-color: rgba(100, 100, 100, 0.0);");}
}
