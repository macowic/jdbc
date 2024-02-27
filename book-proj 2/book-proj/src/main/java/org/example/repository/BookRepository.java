package org.example.repository;

import org.example.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.repository.BaseRepository.getConnection;


public class BookRepository {

    public void createBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, genre, publisher, year, price, shop_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getYear());
            pstmt.setInt(6, book.getPrice());
            if (book.getShop() != null) {
                pstmt.setInt(7, book.getShop().getId());
            } else {
                pstmt.setNull(7, java.sql.Types.INTEGER);
            }


            pstmt.executeUpdate();
        }
    }

    public Book readBook(int id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));
                book.setPrice(rs.getInt("price"));
                return book;
            } else {
                return null;
            }
        }
    }
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));
                book.setPrice(rs.getInt("price"));
                book.setShop(new ShopRepository().readShop(rs.getInt("shop_id")));
                books.add(book);
            }
        }

        return books;
    }
    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET title = ?, author = ?, genre = ?, publisher = ?, year = ?, price = ?, shop_id = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getYear());
            pstmt.setInt(5, book.getPrice());
            pstmt.setInt(7, book.getShop().getId());
            pstmt.setInt(8, book.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }

    public Book getBookByName(String name) throws SQLException {
        String sql = "SELECT * FROM books WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));
                book.setPrice(rs.getInt("price"));
                return book;
            } else {
                return null;
            }
        }
    }

}
