package org.example;

import java.sql.*;

public class AuthorDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into authors (name) values (?)")) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            }
        } else {
            System.out.println("Connection is null");
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "select id from authors where name='" + name + "'")) {
                return rs.next() ? rs.getInt(1) : null;
            }
        } else {
            System.out.println("Connection is null");
            return null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "select name from authors where id=" + id)) {
                return rs.next() ? rs.getString(1) : null;
            }
        } else {
            System.out.println("Connection is null");
            return null;
        }
    }
}