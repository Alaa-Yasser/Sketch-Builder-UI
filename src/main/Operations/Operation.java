package main.Operations;

import main.controller.DrawCanvas;

public abstract class Operation {

    protected DrawCanvas canvas;

    public abstract void setCanvas(DrawCanvas canvas);
    public abstract void operate ();

}
