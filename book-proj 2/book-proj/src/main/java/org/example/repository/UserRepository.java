package org.example.repository;

import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.repository.BaseRepository.getConnection;

public class UserRepository {

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (money, name, email, is_admin, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getMoney());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setBoolean(4, user.isAdmin());
            pstmt.setString(5, user.getPassword());

            pstmt.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMoney(rs.getInt("money"));
                users.add(user);
            }
        }

        return users;
    }
    public User readUser(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setMoney(rs.getInt("money"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));

                return user;
            } else {
                return null;
            }
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET money = ?, name = ?, email = ?, is_admin = ?, password = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, user.getMoney());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setBoolean(4, user.isAdmin());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, user.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }

    }

    public User readUserBooks(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setMoney(rs.getInt("money"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));

                return user;
            } else {
                return null;
            }
        }
    }

    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setMoney(rs.getInt("money"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAdmin(rs.getBoolean("is_admin"));
                return user;
            } else {
                return null;
            }
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setMoney(rs.getInt("money"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));

                return user;
            } else {
                return null;
            }
        }
    }
}
