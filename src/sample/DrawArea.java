package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import java.io.*;

public class DrawArea  extends Canvas {

    private GraphicsContext graphics;
    private int operation;
    private Color mainColor;

    final static int BRUSH = 0;
    final static int ERASER = 1;
    final static int LINE = 2;

    public boolean isSaved = true;

    private FileInputStream cursorImgStream;
    private Image cursorImage;

    //CONSTRACTOUR
    public DrawArea () throws Exception {

        graphics = this.getGraphicsContext2D();

        //CHANGE CURSOR
        cursorImgStream = new FileInputStream("icons/icons8-paint-filled-100.png");
        cursorImage = new Image (cursorImgStream);
        this.setCursor(new ImageCursor(cursorImage));

        // EVENT ON MOUSE PRESSED
        this.setOnMousePressed(e -> {
          graphics.beginPath();

          if (operation == BRUSH) {
             drawLine(e.getX(), e.getY());
          }

//          from_x = e.getX();
//          from_y = e.getY();

          graphics.stroke();

        });

        // EVENT ON MOUSE DRAGGED
        this.setOnMouseDragged(e -> {

//            stackPane.getChildren().remove(0);
//            try {
//                final DrawArea temp_drawArea = new DrawArea(stackPane);
//                final GraphicsContext gc = temp_drawArea.getGraphicsContext2D();
//                to_x = e.getSceneX();
//                to_y = e.getSceneY();
//                drawLine(gc,from_x, from_y, to_x, to_y);
//                stackPane.getChildren().add(0, temp_drawArea);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }

            if (operation == BRUSH) {
               drawLine(e.getX(), e.getY());
            }

            if (operation == ERASER) {
                    eraser(e.getX(), e.getY());
            }

            graphics.stroke();
        });

        //EVENT ON MOUSE RELEASED
//        this.setOnMouseReleased(e -> {
//            to_x = e.getX();
//            to_y = e.getY();
//            drawLine(graphics, from_x, from_y, to_x, to_y);
//        });
//        stackPane.getChildren().addAll( new Canvas(), this);
    }

//    public void drawFrame (double width, double height){
//        graphics.setLineWidth(10);
//        graphics.setStroke(Color.RED);
//        graphics.strokeRect(0, 0, width, height);
//    }

    public void drawLine (double x, double y) {
        isSaved = false;
        graphics.setLineWidth(2);
        graphics.setStroke(mainColor);
        graphics.lineTo(x, y);
        //gc.strokeLine(from_x, from_y, to_x, to_y);
    }

    public void eraser (double x, double y) {
        graphics.clearRect(x, y, 15, 15);
    }

    public void clear () {
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void saveImage () {
        WritableImage image = new WritableImage((int)this.getWidth(), (int)this.getHeight());
        this.snapshot(null, image);

        File imagefile = new File(images_Name());
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", imagefile);
        } catch (Exception s) {
            System.err.println("Couldn't save the file!");}

        isSaved = true;
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

    public  void changeCursorImage (ImageCursor imageCursor){
        this.setCursor(imageCursor);
    }

    public void setOperation (int operation) {this.operation = operation;}
    public void setMainColor(Color color){ this.mainColor = color; }

}