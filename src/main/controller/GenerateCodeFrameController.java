package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import main.Operations.SubmitOperation;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;

public class GenerateCodeFrameController {

    @FXML
    FontIcon closeIcon;
    @FXML
    Button submitButton;
    @FXML
    CheckBox imageProcessingCheckBox;
    @FXML
    TextField inputPathText;
    @FXML
    TextField outputPathText;
    @FXML
    FontIcon browseInputPathIcon;
    @FXML
    FontIcon browseOutputPathIcon;
    @FXML
    Button paintBrushButton;


    public void initialize() {
        closeIcon.setOnMousePressed(event -> closeIcon.getScene().getWindow().hide());

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

        paintBrushButton.setOnAction(event -> {
            try {
                new PaintFrame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String getFileName(String path){
        File file = new File(path);
        String tmp = file.getName();
        return (tmp.replace(tmp.substring(tmp.indexOf('.')), ""));
    }
}
