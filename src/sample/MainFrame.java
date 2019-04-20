package sample;

import Operations.ClearOperation;
import Operations.CloseOperation;
import Operations.OpenOperation;
import Operations.Operation;
import Tools.Shape;
import Tools.Tool;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainFrame extends Stage implements Bar.BarListener {

    private Bar menuBar;
    private DrawCanvas drawArea;
    private TitleBar titleBar;
    private StackPane stackpane;
    private BorderPane layout;
    private VBox bars;
    private Scene scene;
    private Rectangle2D primaryScreenBounds;

    public MainFrame (Client client) throws Exception{

        layout = new BorderPane();
        bars = new VBox();
        stackpane = new StackPane();

        drawArea = new DrawCanvas();
        drawArea.widthProperty().bind(stackpane.widthProperty());
        drawArea.heightProperty().bind(stackpane.heightProperty());

        stackpane.getChildren().addAll(new DrawCanvas(), drawArea);

        titleBar = new TitleBar(drawArea);
        menuBar = new Bar(this, client);
        bars.getChildren().addAll(titleBar, menuBar);

        layout.setTop(bars);
        layout.setCenter(stackpane);

        scene = new Scene(layout, 500, 200);

        primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        this.initStyle(StageStyle.UNDECORATED);
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

        menuBar.clickBrush();
    }

    @Override
    public void toolChanged(Tool tool) {
        if(tool instanceof Shape)
            ((Shape)tool).setLayout(stackpane);
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
    public void changeCursor(ImageCursor imageCursor)  { drawArea.setCursor(imageCursor); }

}
