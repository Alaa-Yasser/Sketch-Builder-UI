package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.io.*;
import java.util.Optional;

public class MainFrame extends Stage implements Bar.BarListener {

    private Bar bar;
    private DrawArea drawArea;
    private StackPane stackpane;
//    private Rectangle rectangle;
    private BorderPane layout;
    private Scene scene;
    private Rectangle2D primaryScreenBounds;

    public MainFrame () throws Exception{

        layout = new BorderPane();
        stackpane = new StackPane();

        bar = new Bar(this);

        drawArea = new DrawArea();
        drawArea.setMainColor(bar.getSelectedColor());
        drawArea.setOperation(bar.getOperation());

//        rectangle = new Rectangle(10, 10, 400, 600);
//        rectangle.setFill(Color.TRANSPARENT);
//        rectangle.setStroke(Color.BLACK);
//        rectangle.setStrokeWidth(5);


        drawArea.widthProperty().bind(stackpane.widthProperty());
        drawArea.heightProperty().bind(stackpane.heightProperty());

        stackpane.getChildren().addAll(drawArea); //rectangle

        layout.setTop(bar);
        layout.setCenter(stackpane);

        scene = new Scene(layout, 500, 200);
        scene.getStylesheets().add("/sample/Style.css");

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
            if (drawArea.isSaved == true){
                this.getScene().getWindow().hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("The current image is not saved. Save it?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES){
                    try {
                        saveImage();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else if (result.get() == ButtonType.NO){
                    this.getScene().getWindow().hide();
                }
                else if (result.get() == ButtonType.CANCEL){
                    alert.hide();
                }
            }
    }

    @Override
    public void colorChanged(Color color) { drawArea.setMainColor(bar.getSelectedColor()); }

    @Override
    public void operationChanged(int operation) { drawArea.setOperation(bar.getOperation()); }

    @Override
    public void changeCursor(ImageCursor imageCursor){ drawArea.changeCursorImage(imageCursor); }

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

    @Override
    public void saveImage() { drawArea.saveImage(); }

    @Override
    public void clear() {drawArea.clear();}

    @Override
    public void close() {closeEvent();}

}
