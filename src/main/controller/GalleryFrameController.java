package main.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.*;
import java.util.*;

public class GalleryFrameController {

    @FXML
    BorderPane layout;
    @FXML
    Pane topBorder;
    @FXML
    Pane bottomBorder;
    @FXML
    Pane rightBorder;
    @FXML
    Pane leftBorder;
    @FXML
    HBox titleBar;
    @FXML
    FontIcon closeIcon;
    @FXML
    FontIcon maximizeIcon;
    @FXML
    FontIcon minusIcon;
    @FXML
    MenuItem openImageItem;
    @FXML
    MenuItem drawImageItem;
    @FXML
    MenuItem convertItem;
    @FXML
    MenuItem closeItem;
    @FXML
    Menu toolsMenu;
    @FXML
    MenuItem modifyItem;
    @FXML
    MenuItem convertSelectedItem;
    @FXML
    MenuItem selectAllItem;
    @FXML
    MenuItem deleteAllItem;
    @FXML
    TilePane tilePane;
    @FXML
    Button addButton;

    private double xOffset;
    private double yOffset;

    private ArrayList<File> selectedItems;
    private ArrayList<GalleryImage> galleryImages;
    private ArrayList<File> loadedFiles;

    public void initialize() {
        selectedItems = new ArrayList<>();
        galleryImages = new ArrayList<>();
        loadedFiles = new ArrayList<>();
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            Stage stage = ((Stage)titleBar.getScene().getWindow());

            if(stage.isMaximized())
                stage.setMaximized(false);

            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        titleBar.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2)
                    maximizeWindow();
            }
        });


        closeIcon.setOnMousePressed(event -> close() );

        maximizeIcon.setOnMousePressed(event -> maximizeWindow());

        minusIcon.setOnMousePressed(event -> ((Stage)(minusIcon.getScene().getWindow())).setIconified(true));


        openImageItem.setOnAction(event -> addImageFunc());

        drawImageItem.setOnAction(event -> {
            try {
                new PaintFrame().setParentFrame((Stage)layout.getScene().getWindow());
                (layout.getScene().getWindow()).hide();

            } catch (Exception e) { e.printStackTrace(); }
        });


        convertItem.setOnAction(event -> new GenerateCodeFrame());

        closeItem.setOnAction(event -> close());


        toolsMenu.setOnShown(event -> {
            if (selectedItems.size() == 0){
                modifyItem.setDisable(true);
                convertSelectedItem.setDisable(true);
            }
            else {
                modifyItem.setDisable(false);
                convertSelectedItem.setDisable(false);
            }
        });

        modifyItem.setOnAction(event -> {
            for (int i=0; i< selectedItems.size(); ++i ){
                PaintFrame paintFrame = new PaintFrame(selectedItems.get(i));
            }
        });

        convertSelectedItem.setOnAction(event -> new GenerateCodeFrame(selectedItems));

        selectAllItem.setOnAction(event -> {
            if(selectedItems.size() == galleryImages.size()){
                for(int i =0 ; i < galleryImages.size(); ++i){
                    galleryImages.get(i).deselectImage();
                }
                selectedItems.clear();
                selectAllItem.setText("Select All");
            }else {
                selectedItems.clear();
                for(int i =0 ; i < galleryImages.size(); ++i){
                    galleryImages.get(i).selectImage();
                    selectedItems.add(galleryImages.get(i).getImagefile());
                }

                selectAllItem.setText("UnSelect All");
            }
        });

        deleteAllItem.setOnAction(event -> {
            tilePane.getChildren().remove(1, galleryImages.size() + 1);
            galleryImages.clear();
        });


        addButton.setOnAction(event -> addImageFunc());
        try {
                File f = new File(".log");
                if(f.exists()){
                    FileReader reader = new FileReader(".log");
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line = bufferedReader.readLine();
                    while(line!= null && !line.equals("")){
                        loadedFiles.add((new File(line)));
                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                    reader.close();
                    fillImages(loadedFiles);
                }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }
    private void addImageFunc(){
        final FileChooser fileChooser = new FileChooser();
        List<File> imageFileList = fileChooser.showOpenMultipleDialog(addButton.getScene().getWindow());
        fillImages(imageFileList);

    }

    private void fillImages(List<File> files){
        if (files != null && files.size() > 0) {
            for (File imageFile : files) {
                galleryImages.add(createImageView(imageFile));
                StackPane img = galleryImages.get(galleryImages.size()-1).getView();
                tilePane.getChildren().add(1, img);
            }
        }
    }
    private GalleryImage createImageView (final File imageFile) {
        final GalleryImage galleryImage;
        try{
            galleryImage =  new GalleryImage(imageFile);

            initContextMenu(galleryImage);

            galleryImage.getView().setOnMouseClicked(event -> {

                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2){
                        OpenImageFrame openImageFrame = new OpenImageFrame(imageFile);
                    }
                }
            });
            return galleryImage;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private void initContextMenu (GalleryImage node) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem  item1 = new MenuItem("Select");

        MenuItem item2 = new MenuItem("Modify");
        MenuItem  item3 = new MenuItem("Convert");
        MenuItem item4 = new MenuItem("Delete");

        contextMenu.getItems().addAll(item1, item2, item3, item4);

        item1.setOnAction(event ->{
            if (node.isSelected()) {
                node.deselectImage();
                selectedItems.remove(node.getImagefile());
            }else {
                node.selectImage();
                selectedItems.add(node.getImagefile());
            }
        });

        item2.setOnAction(event -> {
            PaintFrame paintFrame = new PaintFrame(node.getImagefile());
        } );

        item3.setOnAction(event -> {
            GenerateCodeFrame generateCodeFrame = new GenerateCodeFrame(node.getImagefile());
        });

        item4.setOnAction(event -> {
            tilePane.getChildren().remove(node.getView());
            galleryImages.remove(node);
        });

        node.getView().setOnContextMenuRequested(event ->{
            if (!node.isSelected())
                item1.setText("Select");
            else
                item1.setText("UnSelect");
            contextMenu.show(node.getView(), event.getScreenX(), event.getScreenY());
        });
    }

    public void close () {
        try{
            FileWriter writer = new FileWriter(".log");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("");
            for(int i =0; i< galleryImages.size();++i){
                bufferedWriter.write(galleryImages.get(i).getImagefile().toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            writer.close();

            Main.client.sendMessage("exit");
            Platform.exit();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    private void maximizeWindow () {
        Stage stage = ((Stage)titleBar.getScene().getWindow());
        if (stage.isMaximized()){
            topBorder.setCursor(Cursor.V_RESIZE);
            bottomBorder.setCursor(Cursor.V_RESIZE);
            leftBorder.setCursor(Cursor.H_RESIZE);
            rightBorder.setCursor(Cursor.H_RESIZE);
            stage.setMaximized(false);
        }else {
            topBorder.setCursor(Cursor.DEFAULT);
            bottomBorder.setCursor(Cursor.DEFAULT);
            leftBorder.setCursor(Cursor.DEFAULT);
            rightBorder.setCursor(Cursor.DEFAULT);
            stage.setMaximized(true);
        }
    }

}
