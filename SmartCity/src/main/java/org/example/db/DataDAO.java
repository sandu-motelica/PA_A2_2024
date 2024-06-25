package org.example.db;

import org.example.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public static Location getDriverLocation(int driverId) {
        String query = "SELECT * FROM Locations WHERE id = (SELECT location_id FROM Drivers WHERE id = ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, driverId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double x = resultSet.getDouble("x");
                    double y = resultSet.getDouble("y");
                    return new Location(id, name, x, y);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveDriver(Driver driver) {
        String query = "INSERT INTO Drivers (name, location_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setInt(3, driver.getLocationId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Driver> getDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM Drivers";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int locationId = resultSet.getInt("location_id");
                drivers.add(new Driver(id, name, locationId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
    public static List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM Locations";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double x = resultSet.getDouble("x");
                double y = resultSet.getDouble("y");
                locations.add(new Location(id, name, x, y));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
    public static boolean isParkingLot(int id) {
        String query = "SELECT 1 FROM Parkings WHERE location_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Location getLocationById(int id) {
        Location location = null;
        String query = "SELECT * FROM Locations WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    double x = resultSet.getDouble("x");
                    double y = resultSet.getDouble("y");
                    location = new Location(id, name, x, y);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }
    public static List<Parking> getParkings() {
        List<Parking> parkings = new ArrayList<>();
        String query = "SELECT * FROM Parkings";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int locationId = resultSet.getInt("location_id");
                Location tempLocation = getLocationById(locationId);
                String address = resultSet.getString("address");
                int totalSpots = resultSet.getInt("total_spots");
                int occupiedSpots = resultSet.getInt("occupied_spots");
                parkings.add(new Parking(locationId, tempLocation.getName(), address, totalSpots, occupiedSpots, tempLocation.getX(), tempLocation.getY()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkings;
    }

    public static List<Route> getRoutes() {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT location_id1, location_id2 FROM Routes";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int locationId1 = resultSet.getInt("location_id1");
                int locationId2 = resultSet.getInt("location_id2");
                routes.add(new Route(locationId1, locationId2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routes;
    }

    public static Parking getParkingByLocationId(int locationId) {
        Parking parking = null;
        String query = "SELECT * FROM Parkings WHERE location_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, locationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String address = resultSet.getString("address");
                int totalSpots = resultSet.getInt("total_spots");
                int occupiedSpots = resultSet.getInt("occupied_spots");
                parking = new Parking(locationId, address, totalSpots, occupiedSpots);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parking;
    }

    public static int getFreeSpots(int locationId) {
        int freeSpots = 0;
        String query = "SELECT get_free_spots(?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, locationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                freeSpots = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freeSpots;
    }

    public static List<ParkingSpot> getParkingSpots(int locationId) {
        List<ParkingSpot> spots = new ArrayList<>();
        int id = 0;
        String query = "SELECT id FROM parkings WHERE location_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, locationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query = "SELECT spot_number, is_occupied FROM parkingspots WHERE parking_id = ? ORDER BY id ASC";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int spotNumber = rs.getInt("spot_number");
                    boolean isOccupied = rs.getBoolean("is_occupied");
                    spots.add(new ParkingSpot(spotNumber, isOccupied));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spots;
    }


}
