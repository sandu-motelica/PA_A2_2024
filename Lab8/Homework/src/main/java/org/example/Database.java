package org.example;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/Books";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Sandu";
    private static final ComboPooledDataSource cpds;

    static {
        System.setProperty("com.mchange.v2.log.MLog", "com.mchange.v2.log.FallbackMLog");
        System.setProperty("com.mchange.v2.log.FallbackMLog.DEFAULT_CUTOFF_LEVEL", "OFF");

        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(URL);
        cpds.setUser(USER);
        cpds.setPassword(PASSWORD);
    }

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }
}
