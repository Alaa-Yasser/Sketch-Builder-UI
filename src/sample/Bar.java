package sample;

import Operations.*;
import Tools.*;
import javafx.scene.ImageCursor;
import javafx.scene.control.*;
import javafx.scene.image.*;

import java.io.*;

public class Bar extends MenuBar {

    private Menu file;
    private MenuItem iOpen;
    private MenuItem iSave;
    private MenuItem iSubmit;
    private MenuItem iClear;
    private MenuItem iClose;

    private Menu tools;
    private MenuItem iBrush;
    private MenuItem iEraser;
    private MenuItem iLine;
    private MenuItem iRect;

    private FileInputStream ImgSteram;
    private FileInputStream cursorImgStream = null;
    private Image cursorImage;
    private ImageCursor imageCursor;

    private BarListener listener;
    private Client client;

    //INTERFACE  TO CONTROL ACTIONS
    public interface BarListener {
        void toolChanged(Tool tool);
        void doOperation (Operation operation);
        void changeCursor(ImageCursor imageCursor) throws FileNotFoundException;
    }

    //CONSTRACTOUR
    public Bar(final BarListener listener, Client client){
        this.client = client;
        this.listener = listener;

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
                    listener.doOperation(new OpenOperation());
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
                    listener.doOperation(new SaveOperation());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            //SUBMIT
            iSubmit = new MenuItem("Submit");

            iSubmit.setOnAction(e -> listener.doOperation(new SubmitOperation(client)));

            //CLEAR
            iClear = new MenuItem("Clear");

            ImgSteram = new FileInputStream("icons/icons8-broom-64.png");
            ImageView clearImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iClear.setGraphic(clearImg);

            iClear.setOnAction(e -> listener.doOperation(new ClearOperation()));

            //CLOSE
            iClose = new MenuItem("Close");

            ImgSteram = new FileInputStream("icons/icons8-delete-64.png");
            ImageView closeImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iClose.setGraphic(closeImg);

            iClose.setOnAction(e -> listener.doOperation(new CloseOperation()));

            //ADD ITEMS TO FILE MENU
            file.getItems().addAll(iOpen, iSave, iSubmit, iClear, iClose);

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

            iBrush.setOnAction(e -> clickBrush());

            //ERASER
            iEraser = new MenuItem("Eraser");

            ImgSteram = new FileInputStream("icons/icons8-erase-64.png");
            ImageView eraseImg = new ImageView(new Image(ImgSteram, 30,30, false, true));
            iEraser.setGraphic(eraseImg);

            iEraser.setOnAction(e -> {
                listener.toolChanged(new Erase());

                try {
                    cursorImgStream = new FileInputStream("icons/icons8-eraser-filled-100.png");
                    cursorImage = new Image (cursorImgStream);
                    imageCursor = new ImageCursor(cursorImage);
                    listener.changeCursor(imageCursor);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            });

            iLine = new MenuItem("Line");
            iLine.setOnAction(e -> listener.toolChanged(new Line()));

            iRect = new MenuItem("Rectangle");
            iRect.setOnAction(e -> listener.toolChanged(new RectangleShape()) );

            //ADD ITEMS TO TOOLS MENU
            tools.getItems().addAll(iBrush,iLine, iRect ,iEraser);

            //ADD MENUS TO MENU BAR
            this.getMenus().addAll(file, tools);

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public void clickBrush (){
        listener.toolChanged(new Brush());

        try {
            cursorImgStream = new FileInputStream("icons/icons8-paint-filled-100.png");
            cursorImage = new Image (cursorImgStream);
            imageCursor = new ImageCursor(cursorImage);
            listener.changeCursor(imageCursor);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

}