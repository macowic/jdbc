package org.example.repository;

import org.example.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.repository.BaseRepository.getConnection;

public class WishlistRepository {

    public void addToWishlist(int userId, int bookId) throws SQLException {
        String sql = "INSERT INTO user_wishlist (user_id, book_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, bookId);

            pstmt.executeUpdate();
        }
    }

    public List<Book> getAllBooksInWishlist(int userId) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT books.* FROM user_wishlist " +
                "JOIN books ON books.id = user_wishlist.book_id " +
                "WHERE user_wishlist.user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));
                book.setPublisher(rs.getString("publisher"));
                book.setYear(rs.getInt("year"));
                books.add(book);
            }
        }

        return books;
    }
}
