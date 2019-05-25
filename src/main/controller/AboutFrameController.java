package main.controller;

import javafx.fxml.FXML;
import org.kordamp.ikonli.javafx.FontIcon;

public class AboutFrameController {

    @FXML
    FontIcon closeIcon;

    public void initialize() {

        closeIcon.setOnMousePressed(event -> closeIcon.getScene().getWindow().hide());

    }


}
