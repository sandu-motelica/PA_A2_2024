package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
public class Importer {
    public void importData(String csvFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> r = reader.readAll();
            r.remove(0); // remove header
            AuthorDAO authorDAO = new AuthorDAO();
            BookDAO bookDAO = new BookDAO();

            try (Connection connection = Database.getConnection()) {
                int count = 0;
                for (String[] strings : r) {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title, language, number_of_pages) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, strings[1]);
                    ps.setString(2, strings[6]);
                    ps.setInt(3, Integer.parseInt(strings[7]));
                    ps.executeUpdate();

                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int bookId = rs.getInt(1);
                        Integer authorId = authorDAO.findByName(strings[2]);
                        if (authorId == null) {
                            authorDAO.create(strings[2]);
                            authorId = authorDAO.findByName(strings[2]);
                        }
                        try (PreparedStatement pstmt = connection.prepareStatement(
                                "insert into book_authors (book_id, author_id) values (?, ?)")) {
                            pstmt.setInt(1, bookId);
                            pstmt.setInt(2, authorId);
                            pstmt.executeUpdate();
                        }
                    }
                    count++;
                    if (count == 10) break;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

}
