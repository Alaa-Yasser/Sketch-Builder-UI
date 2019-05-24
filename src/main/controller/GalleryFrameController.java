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
    MenuItem paintItem;
    @FXML
    MenuItem converterItem;
    @FXML
    MenuItem closeItem;
    @FXML
    Menu toolsMenu;
    @FXML
    MenuItem modifySelectedItem;
    @FXML
    MenuItem convertSelectedItem;
    @FXML
    MenuItem selectAllItem;
    @FXML
    MenuItem deleteSelectedItem;
    @FXML
    MenuItem deleteAllItem;
    @FXML
    TilePane tilePane;
    @FXML
    Button addButton;

    private double xOffset;
    private double yOffset;

    private ArrayList<GalleryImage> selectedItems;
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

        paintItem.setOnAction(event -> {
            try {
                new PaintFrame().setParentFrame((Stage)layout.getScene().getWindow());
                (layout.getScene().getWindow()).hide();

            } catch (Exception e) { e.printStackTrace(); }
        });


        converterItem.setOnAction(event -> new GenerateCodeFrame());

        closeItem.setOnAction(event -> close());


        toolsMenu.setOnShown(event -> {
            if (selectedItems.size() == 0){
                modifySelectedItem.setDisable(true);
                convertSelectedItem.setDisable(true);
                deleteSelectedItem.setDisable(true);
            }
            else {
                modifySelectedItem.setDisable(false);
                convertSelectedItem.setDisable(false);
                deleteSelectedItem.setDisable(false);
            }
        });

        modifySelectedItem.setOnAction(event -> modifySelected());

        convertSelectedItem.setOnAction(event -> convertSelected());

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
                    selectedItems.add(galleryImages.get(i));
                }

                selectAllItem.setText("UnSelect All");
            }
        });

        deleteSelectedItem.setOnAction(event -> deleteSelected());

        deleteAllItem.setOnAction(event -> {
            tilePane.getChildren().remove(1, galleryImages.size() + 1);
            galleryImages.clear();
            selectedItems.clear();
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
        final FileChooser FILE_CHOOSER = new FileChooser();
        List<File> imageFileList = FILE_CHOOSER.showOpenMultipleDialog(addButton.getScene().getWindow());
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
    private GalleryImage createImageView (final File IMAGE_FILE) {
        final GalleryImage GALLERY_IMAGE;
        try{
            GALLERY_IMAGE =  new GalleryImage(IMAGE_FILE);

            initContextMenu(GALLERY_IMAGE);

            GALLERY_IMAGE.getView().setOnMouseClicked(event -> {

                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2){
                        OpenImageFrame openImageFrame = new OpenImageFrame(IMAGE_FILE);
                    }
                }
            });
            return GALLERY_IMAGE;
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
                selectedItems.remove(node);
            }else {
                node.selectImage();
                selectedItems.add(node);
            }
        });

        item2.setOnAction(event -> {
            if (selectedItems.size() == 0) {
                new PaintFrame(node.getImagefile());
            } else {
                modifySelected();
            }

        } );

        item3.setOnAction(event -> {
            if (selectedItems.size() == 0) {
                new GenerateCodeFrame(node.getImagefile());
            } else
                convertSelected();

        });

        item4.setOnAction(event -> {
            if (selectedItems.size() == 0) {
                tilePane.getChildren().remove(node.getView());
                galleryImages.remove(node);
                selectedItems.remove(node);
            } else
                deleteSelected();
        });

        node.getView().setOnContextMenuRequested(event ->{
            if (!node.isSelected())
                item1.setText("Select");
            else
                item1.setText("UnSelect");
            if(selectedItems.size() >0) {
                item2.setText("Modify Selected");
                item3.setText("Convert Selected");
                item4.setText("Delete Selected");
            }
            else{
                item2.setText("Modify");
                item3.setText("Convert");
                item4.setText("Delete");
            }
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

    private void modifySelected () {
        for (int i=0; i< selectedItems.size(); ++i ){
            PaintFrame paintFrame = new PaintFrame(selectedItems.get(i).getImagefile());
        }
    }

    private void convertSelected () {
        ArrayList<File> selectedFiles = new ArrayList<>();
        for (int i = 0; i < selectedItems.size(); ++i) {
            selectedFiles.add(selectedItems.get(i).getImagefile());
        }
        new GenerateCodeFrame(selectedFiles);
    }

    private void deleteSelected () {
        for (int i = 0; i < selectedItems.size();) {
            tilePane.getChildren().remove(selectedItems.get(i).getView());
            galleryImages.remove(selectedItems.get(i));
            selectedItems.remove(selectedItems.get(i));
        }
    }

}
