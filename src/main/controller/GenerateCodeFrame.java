package main.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;

public class GenerateCodeFrame extends Stage {

    private Scene scene;
//    private StackPane layout;
//    private VBox vBox;
//    private GridPane grid;
//
//    private TextField inputImgPath;
//    private Button inputImg;
//    private String inputPath;
//
//    private TextField outputImgPath;
//    private Button outputImg;
//    private String outputPath;
//
//    private Button openButton;
//    private Button submitButton;

    public GenerateCodeFrame()
    {
//        inputImgPath = new TextField("Input Image");
//        inputImgPath.setDisable(true);
//
//        inputImg = new Button("...");
//        inputImg.setOnAction(e -> {
//            FileChooser.ExtensionFilter imageFilter
//                    = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", ".jpeg");
//            final FileChooser f = new FileChooser();
//            f.getExtensionFilters().add(imageFilter);
//            File file = f.showOpenDialog(this);
//
//            if (file != null) {
//                inputPath = file.toString();
//                inputImgPath.setText(inputPath);
//            }
//
//
//        });
//
//        outputImgPath = new TextField("Folder Path");
//        outputImgPath.setDisable(true);
//
//        outputImg = new Button("...");
//        outputImg.setOnAction(e -> {
//            final DirectoryChooser directoryChooser = new DirectoryChooser();
//            File selectedDirectory = directoryChooser.showDialog(this);
//
//            if(selectedDirectory != null){
//                outputPath = selectedDirectory.getAbsolutePath();
//                outputImgPath.setText(outputPath);
//            }
//        });
//
//        grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.add(inputImgPath, 0, 0);
//        grid.add(inputImg, 1,0);
//        grid.add(outputImgPath, 0, 1);
//        grid.add(outputImg, 1,1);
//
//        openButton = new Button("Paint Brush");
//        openButton.setPrefWidth(200);
//        openButton.setOnAction(e -> {
//            try {
//                new PaintFrame(client);
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        });
//        CheckBox checkBox = new CheckBox("Use Image Processing");
//        submitButton = new Button("Submit");
//        submitButton.setPrefWidth(200);
//        submitButton.setOnAction(e -> {
//            String flag;
//            if(checkBox.isSelected())
//                flag = "-d";
//            else
//                flag = "-p";
//            new SubmitOperation("compile "+ flag + " " + this.inputPath, getFileName(this.inputPath),
//                this.outputPath).operate();});
//
//
//        vBox = new VBox(5);
//        vBox.getChildren().addAll(checkBox, grid, submitButton, openButton);
//        vBox.setAlignment(Pos.CENTER);
//
//        layout = new StackPane();
//        layout.getChildren().add(vBox);

        try{
           // FXMLLoader loader = new FXMLLoader();
            Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/GenerateCodeFrame.fxml"));
          //  String cssPath = this.getClass().getResource("/resources/css/GenerateCodeFrameStyle.css").toExternalForm();
            //loader.setController(this);
            scene = new Scene(root);
           // scene.getStylesheets().add(cssPath);
        }catch (IOException ex){
            ex.printStackTrace();
        }


        this.setOnCloseRequest(event -> {
            closeServer();
            System.exit(0);
        });


        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.setResizable(false);
        this.show();

    }

    private void closeServer(){
        Main.client.sendMessage("exit");
    }

}
