package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            var authors = new AuthorDAO();
//            authors.create("William Shakespeare");
            printAllAuthors();
            System.out.println(authors.findByName("William Shakespeare"));
            System.out.println(authors.findById(2));


            Database.getConnection().commit();
            Database.closeConnection();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }

    private static void printAllAuthors() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from authors")) {
            System.out.println("All Authors:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }
}