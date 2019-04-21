package sample;

import Operations.SubmitOperation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.File;

public class MainFrame extends Stage {

    private Scene scene;
    private StackPane layout;
    private VBox vBox;
    private GridPane grid;

    private TextField inputImgPath;
    private Button inputImg;
    private String inputPath;

    private TextField outputImgPath;
    private Button outputImg;
    private String outputPath;

    private Button openButton;
    private Button submitButton;

    public MainFrame (Client client)
    {
        inputImgPath = new TextField("Input Image");
        inputImgPath.setDisable(true);

        inputImg = new Button("...");
        inputImg.setOnAction(e -> {
            FileChooser.ExtensionFilter imageFilter
                    = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", ".jpeg");
            final FileChooser f = new FileChooser();
            f.getExtensionFilters().add(imageFilter);
            File file = f.showOpenDialog(this);

            if (file != null) {
                inputPath = file.toString();
                inputImgPath.setText(inputPath);
            }
        });

        outputImgPath = new TextField("Folder Path");
        outputImgPath.setDisable(true);

        outputImg = new Button("...");
        outputImg.setOnAction(e -> {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(this);

            if(selectedDirectory != null){
                outputPath = selectedDirectory.getAbsolutePath();
                outputImgPath.setText(outputPath);
            }
        });

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(inputImgPath, 0, 0);
        grid.add(inputImg, 1,0);
        grid.add(outputImgPath, 0, 1);
        grid.add(outputImg, 1,1);

        openButton = new Button("Paint Brush");
        openButton.setPrefWidth(200);
        openButton.setOnAction(e -> {
            try {
                new PaintFrame(client);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        submitButton = new Button("Submit");
        submitButton.setPrefWidth(200);
        submitButton.setOnAction(e -> new SubmitOperation(client));

        vBox = new VBox(5);
        vBox.getChildren().addAll(grid, submitButton, openButton);
        vBox.setAlignment(Pos.CENTER);

        layout = new StackPane();
        layout.getChildren().add(vBox);

        scene = new Scene(layout, 250, 250);

        this.setTitle("Sketch Builder");
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }
}
