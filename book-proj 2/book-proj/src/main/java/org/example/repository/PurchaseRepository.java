package org.example.repository;

import org.example.models.Book;
import org.example.models.Purchase;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.repository.BaseRepository.getConnection;

public class PurchaseRepository {

    public void createPurchase(int user_id, String title) throws SQLException {
        String checkBalanceSql = "SELECT money FROM users WHERE id = ?";
        String getPriceSql = "SELECT price FROM books WHERE title = ?";
        String purchaseSql = "INSERT INTO purchase (user_id, book_id) VALUES (?, ?)";
        String updateBalanceSql = "UPDATE users SET money = money - ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement checkBalanceStmt = conn.prepareStatement(checkBalanceSql);
             PreparedStatement getPriceStmt = conn.prepareStatement(getPriceSql)) {

            checkBalanceStmt.setInt(1, user_id);
            ResultSet rs = checkBalanceStmt.executeQuery();

            int balance = 0;
            if (rs.next()) {
                balance = rs.getInt("money");
            }

            getPriceStmt.setString(1, title);
            rs = getPriceStmt.executeQuery();

            int bookPrice = 0;
            if (rs.next()) {
                bookPrice = rs.getInt("price");
            }

            if (balance < bookPrice) {
                throw new SQLException("Insufficient balance");
            }

            try (PreparedStatement purchaseStmt = conn.prepareStatement(purchaseSql);
                 PreparedStatement updateBalanceStmt = conn.prepareStatement(updateBalanceSql)) {

                purchaseStmt.setInt(1, user_id);
                purchaseStmt.setString(2, title);

                purchaseStmt.executeUpdate();

                updateBalanceStmt.setInt(1, bookPrice);
                updateBalanceStmt.setInt(2, user_id);

                updateBalanceStmt.executeUpdate();
            }
        }
    }

    public List<Purchase> getAllPurchases() throws SQLException {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT users.id as user_id, users.name as user_name, books.id as book_id, books.title as book_title " +
                "FROM purchase " +
                "JOIN users ON users.id = purchase.user_id " +
                "JOIN books ON books.id = purchase.book_id";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Purchase purchase = new Purchase();
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("user_name"));
                purchase.setUser(user);

                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setTitle(rs.getString("book_title"));
                purchase.setBook(book);

                purchases.add(purchase);
            }
        }

        return purchases;
    }
    public List<Book> getAllBooksByUser(String userName) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT books.* FROM purchase " +
                "JOIN books ON books.id = purchase.book_id " +
                "JOIN users ON users.id = purchase.user_id " +
                "WHERE users.name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);
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




