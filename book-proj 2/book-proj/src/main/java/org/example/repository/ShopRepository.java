package org.example.repository;

import org.example.models.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.repository.BaseRepository.getConnection;

public class ShopRepository {

    public void createShop(Shop shop) throws SQLException {
        String sql = "INSERT INTO shop (name, latitude, longitude) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, shop.getName());
            pstmt.setLong(2, shop.getLatitude());
            pstmt.setLong(3, shop.getLongitude());

            pstmt.executeUpdate();
        }
    }

    public Shop readShop(int id) throws SQLException {
        String sql = "SELECT * FROM shop WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Shop shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setName(rs.getString("name"));
                shop.setLatitude(rs.getLong("latitude"));
                shop.setLongitude(rs.getLong("longitude"));
                return shop;
            } else {
                return null;
            }
        }
    }

    public List<Shop> getAllShops() throws SQLException {
        List<Shop> shops = new ArrayList<>();
        String sql = "SELECT * FROM shop";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Shop shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setName(rs.getString("name"));
                shop.setLatitude(rs.getLong("latitude"));
                shop.setLongitude(rs.getLong("longitude"));
                shops.add(shop);
            }
        }

        return shops;
    }

    public Shop readShopByName(String name) throws SQLException {
        String sql = "SELECT * FROM shop WHERE name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Shop shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setName(rs.getString("name"));
                shop.setLatitude(rs.getLong("latitude"));
                shop.setLongitude(rs.getLong("longitude"));
                return shop;
            } else {
                return null;
            }
        }
    }
    public void updateShop(Shop shop) throws SQLException {
        String sql = "UPDATE shop SET name = ?, latitude = ?, longitude = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, shop.getName());
            pstmt.setLong(2, shop.getLatitude());
            pstmt.setLong(3, shop.getLongitude());
            pstmt.setInt(4, shop.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteShop(int id) throws SQLException {
        String sql = "DELETE FROM shop WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
