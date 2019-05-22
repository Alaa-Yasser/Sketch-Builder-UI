package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;

public class GalleryImage {

    private StackPane root;
    private FXMLLoader loader;
    private boolean selected ;
    private File imagefile;

    public GalleryImage(File imageFile) throws IOException {

        selected = false;
        this.imagefile = imageFile;
        loader = new FXMLLoader(getClass().getResource("/resources/fxml/GalleryImage.fxml"));
        root = loader.load();

        ((GalleryImageController)loader.getController()).setImageView(imageFile);

    }

    public StackPane getView(){
        return root;
    }

    public void selectImage(){
        ((GalleryImageController)loader.getController()).select();
        selected = true;
    }

    public void deselectImage(){
        ((GalleryImageController)loader.getController()).deselect();
        selected = false;
    }

    public boolean isSelected () {return selected;}

    public File getImagefile(){
        return this.imagefile;
    }
}
