package sample;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bar extends MenuBar {

    private Menu file;
    private MenuItem iOpen;
    private MenuItem iSave;
    private MenuItem iClear;
    private MenuItem iClose;

    private Menu tools;
    private MenuItem iBrush;
    private MenuItem iColor;
    private MenuItem iEraser;

    private ColorPicker color;
    private Color selectedColor = Color.BLACK;

    private FileInputStream ImgSteram;
    private FileInputStream cursorImgStream = null;
    private Image cursorImage;
    private ImageCursor imageCursor;

    private int operation = DrawArea.BRUSH;

    //INTERFACE  TO CONTROL ACTIONS
    public interface BarListener {
        void colorChanged(Color color);
        void operationChanged(int operation);
        void changeCursor(ImageCursor imageCursor) throws FileNotFoundException;
        void open() throws Exception;
        void saveImage() throws Exception;
        void clear();
        void close();
    }

    //CONSTRACTOUR
    public Bar(final BarListener listener){

        try{
            //FILE MENU
            file = new Menu("File");

            ImgSteram = new FileInputStream("icons/icons8-file-64.png");
            ImageView fileImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            file.setGraphic(fileImg);

            //FILE'S MENU ITEMS AND THEIR ACTIONS

            //OPEN
            iOpen = new MenuItem("Open");

            ImgSteram = new FileInputStream("icons/icons8-opened-folder-64.png");
            ImageView openImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iOpen.setGraphic(openImg);

            iOpen.setOnAction(e -> {
                try {
                    listener.open();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            //SAVE
            iSave = new MenuItem("Save");

            ImgSteram = new FileInputStream("icons/icons8-save-64.png");
            ImageView saveImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iSave.setGraphic(saveImg);

            iSave.setOnAction(e -> {
                try {
                    listener.saveImage();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            //CLEAR
            iClear = new MenuItem("Clear");

            ImgSteram = new FileInputStream("icons/icons8-broom-64.png");
            ImageView clearImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iClear.setGraphic(clearImg);

            iClear.setOnAction(e -> listener.clear());

            //CLOSE
            iClose = new MenuItem("Close");

            ImgSteram = new FileInputStream("icons/icons8-delete-64.png");
            ImageView closeImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iClose.setGraphic(closeImg);

            iClose.setOnAction(e -> listener.close());

            //ADD ITEMS TO FILE MENU
            file.getItems().addAll(iOpen, iSave, iClear, iClose);

            //***************************************************

            //TOOLS MENU
            tools = new Menu("Tools");

            FileInputStream toolsImgSteram = new FileInputStream("icons/paint-brush-icon-55306.png");
            ImageView toolsImg = new ImageView(new Image(toolsImgSteram, 30,30, false, true));
            tools.setGraphic(toolsImg);

            //TOOLS' MENU ITEMS AND THEIR ACTIONS

            //BRUSH
            iBrush = new MenuItem("Brush");

            ImgSteram = new FileInputStream("icons/icons8-paint-64.png");
            ImageView brushImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iBrush.setGraphic(brushImg);

            iBrush.setOnAction(e ->{
                operation = DrawArea.BRUSH;
                listener.operationChanged(operation);

                try {
                    cursorImgStream = new FileInputStream("icons/icons8-paint-filled-100.png");
                    cursorImage = new Image (cursorImgStream);
                    imageCursor = new ImageCursor(cursorImage);
                    listener.changeCursor(imageCursor);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            });

            //COLOR
            iColor = new MenuItem("Color");

            ImgSteram = new FileInputStream("icons/icons8-color-palette-64.png");
            ImageView colorImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iColor.setGraphic(colorImg);

            iColor.setOnAction(e -> {
                Stage colorWindow = new Stage();
                colorWindow.setTitle("Change Color");
                colorWindow.initModality(Modality.APPLICATION_MODAL);
                colorWindow.setResizable(false);

                color = new ColorPicker();
                color.setValue(selectedColor);
                color.setOnAction(e1 -> {
                    selectedColor = color.getValue();
                    listener.colorChanged(selectedColor);
                });

                colorWindow.setScene(new Scene (color,250,50));
                colorWindow.show();
            });

            //ERASER
            iEraser = new MenuItem("Eraser");

            ImgSteram = new FileInputStream("icons/icons8-erase-64.png");
            ImageView eraseImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iEraser.setGraphic(eraseImg);

            iEraser.setOnAction(e -> {
                operation = DrawArea.ERASER;
                listener.operationChanged(operation);

                try {
                    cursorImgStream = new FileInputStream("icons/icons8-eraser-filled-100.png");
                    cursorImage = new Image (cursorImgStream);
                    imageCursor = new ImageCursor(cursorImage);
                    listener.changeCursor(imageCursor);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            });

            //ADD ITEMS TO TOOLS MENU
            tools.getItems().addAll(iBrush, iColor, iEraser);

            //ADD MENUS TO MENU BAR
            this.getMenus().addAll(file, tools);

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public Color getSelectedColor (){ return selectedColor; }
    public int getOperation () { return operation; }

}