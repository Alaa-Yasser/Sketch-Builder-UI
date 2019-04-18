package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
public class MainFrame extends Stage implements Bar.BarListener {

    private Bar bar;
    private DrawCanvas drawArea;
    private StackPane stackpane;
    private BorderPane layout;
    private Scene scene;
    private Rectangle2D primaryScreenBounds;

    public MainFrame () throws Exception{

        layout = new BorderPane();
        stackpane = new StackPane();

        bar = new Bar(this);

        drawArea = new DrawCanvas();


        drawArea.widthProperty().bind(stackpane.widthProperty());
        drawArea.heightProperty().bind(stackpane.heightProperty());

        stackpane.getChildren().addAll(new DrawCanvas(), drawArea);

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
            CloseOperation close = new CloseOperation();
            close.setCanvas(drawArea);
            close.operate();
        });

        bar.clickBrush();
    }

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
        else  if (operation instanceof ClearOperation)
            ((ClearOperation)operation).setLayout(stackpane);
        operation.setCanvas(drawArea);
        operation.operate();
    }

    @Override
    public void changeCursor(ImageCursor imageCursor){  }

}
