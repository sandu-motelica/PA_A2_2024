package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.example.db.DataDAO;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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
        mainStage.setTitle("City Parking Map");

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

    public static void main(String[] args) {
        launch(args);
    }
}
