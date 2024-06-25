package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.db.DataDAO;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SmartCity");
        primaryStage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toExternalForm()));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        ImageView imageView = new ImageView(new Image("file:src/main/resources/images/login.png"));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        grid.add(imageView, 0, 0, 2, 1);

        Text scenetitle = new Text("Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 1, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 2);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 2);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 3);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 3);

        Button btn = new Button("Login");
        grid.add(btn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white;");
        grid.setStyle("-fx-background-color: #f0f0f0;");

        btn.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();

            int driverId = Authenticator.authenticateDriver(username, password);
            if (driverId == -1) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Invalid username or password.");
            } else {
                primaryStage.close();
                openMainApplication(driverId);
            }
        });

        Scene scene = new Scene(grid, 400, 375);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openMainApplication(int driverId) {
        Stage mainStage = new Stage();
        mainStage.setTitle("SmartCity");
        mainStage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toExternalForm()));
        City city = new City("Iași");
        Location driverLocation = DataDAO.getDriverLocation(driverId);
        city.addLocation(driverLocation);

        List<Location> locations = DataDAO.getLocations();
        for(Location location: locations){
            city.addLocation(location);
        }

        Pane root = new Pane();
        Canvas canvas = new Canvas(900, 600);
        Image mapImage = new Image(System.getProperty("user.dir") + "/src/main/resources/images/map.png");
        Image parkingIcon = new Image(System.getProperty("user.dir") + "/src/main/resources/images/parkingIcon.png");
        drawMap(canvas.getGraphicsContext2D(), city, mapImage, parkingIcon, driverLocation);

        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();

            Location clickedLocation = getClickedLocation(city, x, y);
            if (clickedLocation != null && DataDAO.isParkingLot(clickedLocation.getId())) {
                Parking parking = DataDAO.getParkingByLocationId(clickedLocation.getId());
                showParkingDetails(parking);
            }
        });

        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 900, 600);

        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.setFullScreen(false);
        mainStage.show();
    }

    private void drawMap(GraphicsContext gc, City city, Image mapImage, Image parkingIcon, Location driverLocation) {
        gc.drawImage(mapImage, 0, 0, 900, 600);
        gc.setFill(Color.BLACK);
        List<Route> routes = DataDAO.getRoutes();
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(2);
        for (Route route : routes) {
            Location location1 = city.getLocationById(route.getLocationId1());
            Location location2 = city.getLocationById(route.getLocationId2());
            if (location1 != null && location2 != null) {
                gc.strokeLine(location1.getX(), location1.getY(), location2.getX(), location2.getY());
            }
        }

        for (Location location : city.getLocations()) {
            if (location.equals(driverLocation)) {
                gc.setFill(Color.BLUE);
                gc.fillOval(location.getX(), location.getY(), 10, 10);
            } else if (DataDAO.isParkingLot(location.getId())) {
                double x = location.getX() - 15;
                double y = location.getY() - 15;
                gc.drawImage(parkingIcon, x, y, 30, 30);
            } else {
                gc.setFill(Color.GRAY);
                gc.fillOval(location.getX(), location.getY(), 10, 10);
            }
        }
    }

    private Location getClickedLocation(City city, double x, double y) {
        for (Location location : city.getLocations()) {
            double locationX = location.getX();
            double locationY = location.getY();
            double radius = 15;

            if (Math.pow(x - locationX, 2) + Math.pow(y - locationY, 2) <= Math.pow(radius, 2)) {
                return location;
            }
        }
        return null;
    }

    private void showParkingDetails(Parking parking) {
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Parking Details");
        detailsStage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toExternalForm()));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(25));
        vbox.setSpacing(2);
        vbox.setStyle("-fx-background-color: #f0f0f0;");

        Label addressLabel = new Label("Address: " + parking.getAddress());
        addressLabel.setStyle("-fx-font-size: 14pt; -fx-font-weight: 500;");

        Label totalSpotsLabel = new Label("Total Spots: " + parking.getTotalSpots());
        totalSpotsLabel.setStyle("-fx-font-size: 12pt;");

        int freeSpots = DataDAO.getFreeSpots(parking.getId());
        Label freeSpotsLabel = new Label("Free Spots: " + freeSpots);
        freeSpotsLabel.setStyle("-fx-font-size: 12pt; -fx-text-fill: #0ca678;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 0, 20, 0));
        gridPane.setStyle("-fx-background-color: #f0f0f0;");

        List<ParkingSpot> parkingSpots = DataDAO.getParkingSpots(parking.getId());

        for (int i = 0; i < parkingSpots.size(); i++) {
            ParkingSpot spot = parkingSpots.get(i);

            Rectangle spotRect = new Rectangle(30, 38);
            spotRect.setFill(spot.isOccupied() ? Color.GRAY : Color.web("#0ca678"));
            spotRect.setArcWidth(10);
            spotRect.setArcHeight(10);

            Text spotText = new Text(String.valueOf(spot.getSpotNumber()));
            spotText.setFill(Color.WHITE);

            StackPane spotPane = new StackPane();
            spotPane.getChildren().addAll(spotRect, spotText);
            StackPane.setAlignment(spotText, Pos.CENTER);

            gridPane.add(spotPane, i % 6, i / 6);
        }

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: transparent;");


        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #c92a2a; -fx-text-fill: white; -fx-border-radius: 1;");
        closeButton.setOnAction(e -> detailsStage.close());

        vbox.getChildren().addAll(addressLabel, totalSpotsLabel, freeSpotsLabel, scrollPane, closeButton);

        Scene scene = new Scene(vbox);
        detailsStage.setScene(scene);
        detailsStage.sizeToScene();
        detailsStage.setResizable(true);
        detailsStage.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
