package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseRepository {
    private static final String URL = "jdbc:postgresql://localhost/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void createTablesIfNotExist() throws SQLException {
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS books (" +
                    "id SERIAL PRIMARY KEY, " +
                    "title VARCHAR(255), " +
                    "author VARCHAR(255), " +
                    "genre VARCHAR(255), " +
                    "publisher VARCHAR(255), " +
                    "year INT, " +
                    "price INT, " +
                    "shop_id INT)";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS shop (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "latitude BIGINT, " +
                    "longitude BIGINT)";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "money INT, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255), " +
                    "is_admin BOOLEAN DEFAULT FALSE)";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS user_wishlist (" +
                    "user_id INT REFERENCES users(id), " +
                    "book_id INT REFERENCES books(id), " +
                    "PRIMARY KEY (user_id, book_id))";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS purchase_book (" +
                    "user_id INT REFERENCES users(id), " +
                    "book_id INT REFERENCES books(id), " +
                    "PRIMARY KEY (user_id, book_id))";
            stmt.execute(sql);
        }
    }

}