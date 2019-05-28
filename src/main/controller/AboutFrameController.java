package main.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class AboutFrameController {

    @FXML
    FontIcon closeIcon;

    public void initialize() {

        closeIcon.setOnMousePressed(event -> {
            Stage stage = (Stage) closeIcon.getScene().getWindow();
            stage.close();
        });

    }


}
