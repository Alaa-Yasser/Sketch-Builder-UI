package main.Operations;

import main.Operations.Operation;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.*;
import javafx.scene.image.WritableImage;
import main.sample.DrawCanvas;

import javax.imageio.ImageIO;
import java.io.File;

public class SaveOperation extends Operation {

    private GraphicsContext graphics;

    @Override
    public void setCanvas(DrawCanvas canvas) {
        this.canvas = canvas;
        graphics = this.canvas.getGraphicsContext2D();
    }

    @Override
    public void operate() {
            WritableImage image = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
            canvas.snapshot(null, image);

            File imagefile = new File(images_Name());
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", imagefile);
            } catch (Exception s) {
                System.err.println("Couldn't save the file!");}

            canvas.setIsEdited(false);
    }

    public String images_Name ()
    {
        File folder = new File(System.getProperty("user.dir")+"//images");
        File[] listOfFiles = folder.listFiles();

        int lastImageIndex;

        if(listOfFiles == null)
            lastImageIndex = 0;
        else
            lastImageIndex = listOfFiles.length;

        String str = "images/img_" + lastImageIndex + ".png";
        return str;
    }
}
