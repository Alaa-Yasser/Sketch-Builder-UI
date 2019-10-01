package sketch.builder.ui.Operations;

import sketch.builder.ui.controller.DrawCanvas;

public abstract class Operation {

    protected DrawCanvas canvas;

    public abstract void setCanvas(DrawCanvas canvas);
    public abstract void operate ();

}
