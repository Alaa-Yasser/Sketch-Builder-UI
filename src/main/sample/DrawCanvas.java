package main.sample;

import javafx.scene.canvas.Canvas;

public class DrawCanvas extends Canvas {
    private boolean isEdited;

    public boolean getIsEdited () { return isEdited; }
    public void setIsEdited (Boolean isEdited) {this.isEdited = isEdited;}
}
