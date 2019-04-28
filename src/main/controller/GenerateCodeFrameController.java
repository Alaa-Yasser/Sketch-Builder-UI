package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import main.Operations.SubmitOperation;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public class GenerateCodeFrameController {

    @FXML
    BorderPane titleBar;
    @FXML
    FontIcon closeIcon;
    @FXML
    FontIcon minusIcon;
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

        minusIcon.setOnMousePressed(event -> ((Stage)(minusIcon.getScene().getWindow())).setIconified(true));

        browseInputPathIcon.setOnMousePressed(event -> {
            FileChooser.ExtensionFilter imageFilter
                    = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", ".jpeg");
            final FileChooser f = new FileChooser();
            f.getExtensionFilters().add(imageFilter);
            File file = f.showOpenDialog(browseInputPathIcon.getScene().getWindow());

            if (file != null) {
                inputPathText.setText(file.toString());
            }
        });

        browseOutputPathIcon.setOnMousePressed(event -> {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(browseOutputPathIcon.getScene().getWindow());

            if(selectedDirectory != null) {
                outputPathText.setText(selectedDirectory.getAbsolutePath());
            }
        });

        languageComboBox.getItems().addAll("Android", "HTML", "C#");

        submitButton.setOnAction(event ->{
            String flag;
            if(imageProcessingCheckBox.isSelected())
                flag = "-d";
            else
                flag = "-p";
            new SubmitOperation("compile "+ flag + " " + this.inputPathText.getText(),
                    getFileName(this.inputPathText.getText()),
                    this.outputPathText.getText()).operate();
        });

        inputPathText.setFocusTraversable(false);
        outputPathText.setFocusTraversable(false);
    }

    private String getFileName(String path){
        File file = new File(path);
        String tmp = file.getName();
        return (tmp.replace(tmp.substring(tmp.indexOf('.')), ""));
    }
}
