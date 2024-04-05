package lab6.compulsory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

public class ConfigPanel extends HBox {
    final MainFrame frame;
    Label label;

    Spinner<Integer> spinnerCol;
    Spinner<Integer> spinnerRow;
    Button createButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        label = new Label("Grid Size:");

        spinnerCol = new Spinner<>(2, 100, 10, 1);
        spinnerRow = new Spinner<>(2, 100, 10, 1);

        createButton = new Button("Create");
        createButton.setOnAction(event -> {
            int numRow = spinnerCol.getValue();
            int numCol = spinnerRow.getValue();
            frame.updateDrawingPanel(numRow, numCol);
            System.out.println("NewTable size: " + spinnerCol.getValue() + " " + spinnerRow.getValue());
        });


        this.getChildren().addAll(label, spinnerCol,spinnerRow,createButton);


        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        System.out.println(spinnerCol.getValue());

    }

}

