package org.example;


import java.sql.*;

public class BookDAO {
    public void create(Book book) throws SQLException {
        Connection con = Database.getConnection();
        AuthorDAO authorDAO = new AuthorDAO();
        if (con != null) {
            try {
                con.setAutoCommit(false);  // Start transaction

                PreparedStatement pstmt = con.prepareStatement(
                        "insert into books (title, language, publication_date, number_of_pages) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getLanguage());
                pstmt.setDate(3, new java.sql.Date(book.getPublicationDate().getTime()));
                pstmt.setInt(4, book.getNumberOfPages());
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int bookId = rs.getInt(1);
                    for (String author : book.getAuthors()) {
                        Integer authorId = authorDAO.findByName(author);
                        if (authorId == null) {
                            authorDAO.create(author);
                            authorId = authorDAO.findByName(author);
                        }
                        linkAuthorToBook(authorId, bookId);
                    }
                }

                con.commit();  // Commit transaction
            } catch (SQLException e) {
                con.rollback();  // Rollback transaction in case of an error
                throw e;
            }
        } else {
            System.out.println("Connection is null");
        }
    }



    public void linkAuthorToBook(int authorId, int bookId) throws SQLException {
        Connection con = Database.getConnection();
        if (con != null) {
            try (PreparedStatement pstmt = con.prepareStatement(
                    "insert into book_authors (book_id, author_id) values (?, ?)")) {
                pstmt.setInt(1, bookId);
                pstmt.setInt(2, authorId);
                pstmt.executeUpdate();
            }
        } else {
            System.out.println("Connection is null");
        }
    }

}