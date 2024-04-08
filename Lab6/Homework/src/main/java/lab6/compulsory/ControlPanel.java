package lab6.compulsory;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {
    private int size;
    private MainFrame frame;


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));


        Button loadButton = new Button("Load");
        loadButton.setOnAction(event -> {
            frame.loadGame();
            System.out.println("Load game");
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            frame.saveGame();
            System.out.println("Save game");
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> Platform.exit());

        getChildren().addAll(loadButton, saveButton, exitButton);
    }
}
