package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ToolBox extends HBox {

    private Button bPen;
    private ColorPicker color;
    private Spinner size;
    private Button bEraser;
    private Button bClear;
    private Button bSave;

    private Color selectedColor;
    private int selectedSize;

    private int operation = DrawArea.BRUSH;

    public interface ToolBoxListener {
        void colorChanged(Color color);
        void sizeChanged(int size);
        void operationChanged(int operation);
        void saveImage() throws Exception;
        void clear();
    }

    public ToolBox (final ToolBoxListener listener){
        bPen = new Button("Pen");
        bPen.setOnAction(e -> {
            operation = DrawArea.BRUSH;
            listener.operationChanged(operation);
        });

        color = new ColorPicker();
        color.setValue(Color.BLACK);
        color.setOnAction(e -> {
            selectedColor = color.getValue();
            listener.colorChanged(selectedColor);
        });

        size = new Spinner(1, 10, 1);
        size.valueProperty().addListener(e -> {
            int value = (int) size.getValue();
            selectedSize = value;
            listener.sizeChanged(selectedSize);
        });

        bEraser = new Button("Eraeser");
        bEraser.setOnAction(e -> {
            operation = DrawArea.ERASER;
            listener.operationChanged(operation);
        });

        bClear = new Button("Clear");
        bClear.setOnAction(e -> listener.clear());

        bSave = new Button("Save");
        bSave.setOnAction(e -> {
            try {
                listener.saveImage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        this.getChildren().addAll(bPen, color, size, bEraser, bClear, bSave);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(5, 0, 0, 0));

    }

    public Color getSelectedColor (){ return selectedColor; }
    public int getSelectedSize (){ return selectedSize; }
    public int getOperation () { return operation; }

}