package main.Tools;

import main.sample.DrawCanvas;

public abstract class Tool {
    protected double initX, initY;
    protected DrawCanvas canvas;
    protected final int LINE_WIDTH = 2;

    public abstract void setCanvas(DrawCanvas canvas);
    public abstract void draw ();

}