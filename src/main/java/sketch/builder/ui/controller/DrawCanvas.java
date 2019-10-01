package sketch.builder.ui.controller;

import javafx.scene.canvas.Canvas;

import java.io.File;

public class DrawCanvas extends Canvas {
    private boolean isEdited;
    private boolean isOpened;
    private File imageFile;

    public boolean getIsEdited () { return isEdited; }
    public void setIsEdited (Boolean isEdited) {this.isEdited = isEdited;}

    public boolean getIsOpened () { return isOpened; }
    public void setIsOpened (Boolean isOpened) {this.isOpened = isOpened;}

    public File getImageFile () {return imageFile;}
    public void setImageFile (File imageFile) {this.imageFile = imageFile;}

}
