package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            var authors = new AuthorDAO();
//authors.create("William Shakespeare");
            printAllAuthors();
            System.out.println(authors.findByName("William Shakespeare"));
            System.out.println(authors.findById(2));

            //BookDAO bookDAO = new BookDAO();
            //
            //Book book = new Book();
            //book.setTitle("Harry Potter and the Philosopher's Stone");
            //book.setAuthors(List.of("J.K. Rowling"));
            //book.setLanguage("English");
            //book.setPublicationDate(new Date());
            //book.setNumberOfPages(223);
            //bookDAO.create(book);

            printAllBooks();
        } catch (SQLException e) {
            System.err.println(e);
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

    public static void printAllBooks() throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "select b.id, b.title, b.language, b.publication_date, b.number_of_pages, a.name " +
                                 "from books b " +
                                 "join book_authors ba on b.id = ba.book_id " +
                                 "join authors a on a.id = ba.author_id")) {
                while (rs.next()) {
                    System.out.println("Book ID: " + rs.getInt("id"));
                    System.out.println("Title: " + rs.getString("title"));
                    System.out.println("Language: " + rs.getString("language"));
                    System.out.println("Publication Date: " + rs.getDate("publication_date"));
                    System.out.println("Number of Pages: " + rs.getInt("number_of_pages"));
                    System.out.println("Author: " + rs.getString("name"));
                    System.out.println("-----------------------------");
                }
            }
        } else {
            System.out.println("Connection is null");
        }
    }

}