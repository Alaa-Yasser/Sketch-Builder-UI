package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.io.*;
public class MainFrame extends Stage implements Bar.BarListener {

    private Bar bar;
    private DrawCanvas drawArea;
    private StackPane stackpane;
//    private Rectangle rectangle;
    private BorderPane layout;
    private Scene scene;
    private Rectangle2D primaryScreenBounds;

    public MainFrame () throws Exception{

        layout = new BorderPane();
        stackpane = new StackPane();

        bar = new Bar(this);

        drawArea = new DrawCanvas();
//        drawArea.setMainColor(bar.getSelectedColor());


        drawArea.widthProperty().bind(stackpane.widthProperty());
        drawArea.heightProperty().bind(stackpane.heightProperty());

        stackpane.getChildren().addAll(new Canvas(), drawArea); //rectangle

        layout.setTop(bar);
        layout.setCenter(stackpane);

        scene = new Scene(layout, 500, 200);

        primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        this.setTitle("Paint Brush");
        this.setScene(scene);
        this.setHeight(primaryScreenBounds.getHeight());
        this.setResizable(false);
        this.show();

        this.setOnCloseRequest(e -> {
            e.consume();
            closeEvent();
        });
        //drawArea.drawFrame(200,200);
    }

    private void closeEvent (){
            if (true){
//                this.getScene().getWindow().close();
                System.exit(0);
            }
            else
            {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setContentText("The current image is not saved. SaveOperation it?");
//                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.YES){
//                    try {
//                        saveImage();
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                }
//                else if (result.get() == ButtonType.NO){
//                    this.getScene().getWindow().hide();
//                }
//                else if (result.get() == ButtonType.CANCEL){
//                    alert.hide();
//                }
            }
    }

    @Override
    public void colorChanged(Color color) {  }

    @Override
    public void toolChanged(Tool tool) {
        if(tool instanceof Line)
            ((Line)tool).setLayout(stackpane);
        tool.setCanvas(drawArea);
    }

    @Override
    public void doOperation (Operation operation){
        if (operation instanceof OpenOperation)
            ((OpenOperation)operation).setStage(this);
        operation.setCanvas(drawArea);
        operation.operate();
    }

    @Override
    public void changeCursor(ImageCursor imageCursor){  }
//
    @Override
    public void open(){
        final FileChooser f = new FileChooser();
            File file = f.showOpenDialog(this);
            if (file != null) { // only proceed, if file was chosen
              //  MainFrame openFrame = new MainFrame();
                Image img = new Image(file.toURI().toString());
                drawArea.getGraphicsContext2D().drawImage(img, 0, 0);
               // openFrame.drawArea.getGraphicsContext2D().drawImage(img, 0, 0);
            }
    }
//
    @Override
    public void saveImage() { }
//
    @Override
    public void clear() {}

    @Override
    public void close() {}

}
