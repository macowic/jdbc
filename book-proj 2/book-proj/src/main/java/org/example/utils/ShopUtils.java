package org.example.utils;

import org.example.models.Shop;
import org.example.repository.ShopRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ShopUtils {
    public static void manageShops(Scanner scanner, ShopRepository shopRepository) throws SQLException {
        while (true) {
            System.out.println("1. Add a shop");
            System.out.println("2. View a shop");
            System.out.println("3. Update a shop");
            System.out.println("4. Delete a shop");
            System.out.println("5. Get all shops");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addShop(scanner, shopRepository);
                    break;
                case 2:
                    viewShop(scanner, shopRepository);
                    break;
                case 3:
                    updateShop(scanner, shopRepository);
                    break;
                case 4:
                    deleteShop(scanner, shopRepository);
                    break;
                case 5:
                    getAllShops(shopRepository);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void getAllShops(ShopRepository shopRepository) throws SQLException {
        List<Shop> shops = shopRepository.getAllShops();
        for (Shop shop : shops) {
            System.out.println("ID: " + shop.getId());
            System.out.println("Name: " + shop.getName());
            System.out.println("Latitude: " + shop.getLatitude());
            System.out.println("Longitude: " + shop.getLongitude());
            System.out.println("--------------------");
        }
    }

    private static void addShop(Scanner scanner, ShopRepository shopRepository) throws SQLException {
        System.out.print("Enter shop name: ");
        String name = scanner.next();
        System.out.print("Enter shop latitude: ");
        long latitude = scanner.nextLong();
        System.out.print("Enter shop longitude: ");
        long longitude = scanner.nextLong();

        Shop shop = new Shop();
        shop.setName(name);
        shop.setLatitude(latitude);
        shop.setLongitude(longitude);

        shopRepository.createShop(shop);
    }

    private static void viewShop(Scanner scanner, ShopRepository shopRepository) throws SQLException {
        System.out.print("Enter shop ID: ");
        int id = scanner.nextInt();

        Shop shop = shopRepository.readShop(id);
        if (shop != null) {
            System.out.println("Name: " + shop.getName());
            System.out.println("Latitude: " + shop.getLatitude());
            System.out.println("Longitude: " + shop.getLongitude());
        } else {
            System.out.println("Shop not found.");
        }
    }

    private static void updateShop(Scanner scanner, ShopRepository shopRepository) throws SQLException {
        System.out.print("Enter shop ID: ");
        int id = scanner.nextInt();

        Shop shop = shopRepository.readShop(id);
        if (shop != null) {
            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.next();
            if (!name.isEmpty()) {
                shop.setName(name);
            }
            System.out.print("Enter new latitude (leave blank to keep current): ");
            String latitude = scanner.next();
            if (!latitude.isEmpty()) {
                shop.setLatitude(Long.parseLong(latitude));
            }
            System.out.print("Enter new longitude (leave blank to keep current): ");
            String longitude = scanner.next();
            if (!longitude.isEmpty()) {
                shop.setLongitude(Long.parseLong(longitude));
            }

            shopRepository.updateShop(shop);
        } else {
            System.out.println("Shop not found.");
        }
    }

    private static void deleteShop(Scanner scanner, ShopRepository shopRepository) throws SQLException {
        System.out.print("Enter shop ID: ");
        int id = scanner.nextInt();

        shopRepository.deleteShop(id);
    }
}
