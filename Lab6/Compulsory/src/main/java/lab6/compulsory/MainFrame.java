package lab6.compulsory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends Application {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    int sizeX = 10;
    int sizeY = 10;

    public MainFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        canvas = new DrawingPanel(sizeX,sizeY);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);

        root.setTop(configPanel);
        root.setCenter(canvas);
        root.setBottom(controlPanel);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Positional Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void updateDrawingPanel(int sizeX, int sizeY) {
        canvas.updateBoardSize(sizeX, sizeY);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

