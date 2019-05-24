package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.*;
import main.Operations.SubmitOperation;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class GenerateCodeFrameController {

    @FXML
    HBox titleBar;
    @FXML
    FontIcon closeIcon;
    @FXML
    TextField inputPathText;
    @FXML
    FontIcon browseInputPathIcon;
    @FXML
    TextField outputPathText;
    @FXML
    FontIcon browseOutputPathIcon;
    @FXML
    CheckBox imageProcessingCheckBox;
    @FXML
    ComboBox languageComboBox;
    @FXML
    Button submitButton;

    private double xOffset;
    private double yOffset;
    private final String CSHARP_LANGUAGE = "C#";
    private final String HTML_LANGUAGE = "HTML";
    private final String ANDROID_LANGUAGE = "Android";
    private HashMap<String , String> languageMap;


    public void initialize() {

        titleBar.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        titleBar.setOnMouseDragged(e -> {
            (titleBar.getScene().getWindow()).setX(e.getScreenX() - xOffset);
            (titleBar.getScene().getWindow()).setY(e.getScreenY() - yOffset);
        });



        closeIcon.setOnMousePressed(event -> {
            closeIcon.getScene().getWindow().hide();
        });

        browseInputPathIcon.setOnMousePressed(event -> {
            FileChooser.ExtensionFilter imageFilter
                    = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", ".jpeg");
            final FileChooser FILE_CHOOSER = new FileChooser();
            FILE_CHOOSER.getExtensionFilters().add(imageFilter);
            List<File> files = FILE_CHOOSER.showOpenMultipleDialog(browseInputPathIcon.getScene().getWindow());

            if (files.size() != 0) {
               setImagePath(files);
            }
        });

        browseOutputPathIcon.setOnMousePressed(event -> {
            final DirectoryChooser DIRECTORY_CHOOSER = new DirectoryChooser();
            File selectedDirectory = DIRECTORY_CHOOSER.showDialog(browseOutputPathIcon.getScene().getWindow());

            if(selectedDirectory != null) {
                outputPathText.setText(selectedDirectory.getAbsolutePath());
            }
        });

        languageComboBox.getItems().addAll(ANDROID_LANGUAGE, HTML_LANGUAGE, CSHARP_LANGUAGE);
        this.languageMap = new HashMap<>();
        this.languageMap.put(ANDROID_LANGUAGE, "--android");
        this.languageMap.put(HTML_LANGUAGE, "--html");
        this.languageMap.put(CSHARP_LANGUAGE, "--csharp");

        submitButton.setOnAction(event ->{

            if (inputPathText.getText().equals("Image Path") || outputPathText.getText().equals("Project Path")) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Input Path and Project Path are required");
                error.showAndWait();
            }else if (languageComboBox.getValue() == null) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Select Your Language");
                error.showAndWait();
            }else {
                String flag;
                if (imageProcessingCheckBox.isSelected())
                    flag = "-d";
                else
                    flag = "-p";
                System.out.println(this.inputPathText.getText());
                new SubmitOperation("compile " + flag + " " + this.inputPathText.getText(),
                        getFileName(this.inputPathText.getText()),
                        this.outputPathText.getText(),
                        this.languageMap.get(this.languageComboBox.getValue())).operate();
            }
        });

        inputPathText.setFocusTraversable(false);
        outputPathText.setFocusTraversable(false);
    }

    private String getFileName(String path){
        File file = new File(path);
        String tmp = file.getName();
        return (tmp.replace(tmp.substring(tmp.indexOf('.')), ""));
    }

    public void setImagePath (File imageFile) {
        inputPathText.setText( imageFile.toString());
    }


    public void setImagePath (List<File> imagesFiles) {
        String path = "";
        for (File file : imagesFiles){
            path += file.toString() + " ";
        }
        inputPathText.setText(path);
    }
}