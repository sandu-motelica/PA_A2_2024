package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/Books";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Sandu";
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10); // Set your pool size as per your requirement
        ds = new HikariDataSource(config);
    }

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
