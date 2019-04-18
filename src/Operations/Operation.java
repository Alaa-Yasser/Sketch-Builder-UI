package Operations;

import sample.DrawCanvas;

public abstract class Operation {

    protected DrawCanvas canvas;

    public abstract void setCanvas(DrawCanvas canvas);
    public abstract void operate ();

}
