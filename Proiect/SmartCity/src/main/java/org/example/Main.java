package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        City city = new City("MyCity");
        Parking parkingLot = new Parking("Main Street", 10);
        city.addParking(parkingLot);

        Label label = new Label("Welcome to the Parking Management System");
        Button button = new Button("Show Parking Status");

        button.setOnAction(e -> {
            StringBuilder status = new StringBuilder("Parking Status:\n");
            for (ParkingSpot spot : parkingLot.getSpots()) {
                status.append("Spot ").append(spot.getSpotNumber())
                        .append(": ").append(spot.isOccupied() ? "Occupied" : "Free").append("\n");
            }
            label.setText(status.toString());
        });

        VBox vbox = new VBox(label, button);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Parking Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
