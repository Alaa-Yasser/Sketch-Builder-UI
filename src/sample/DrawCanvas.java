package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawCanvas extends Canvas {
    private boolean isEdited;

    public boolean getIsEdited () { return isEdited; }
    public void setIsEdited (Boolean isEdited) {this.isEdited = isEdited;}
}
