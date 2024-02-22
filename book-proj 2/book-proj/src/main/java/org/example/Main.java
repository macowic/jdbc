package org.example;

import java.sql.SQLException;
import java.util.Scanner;

import org.example.models.Book;
import org.example.models.Shop;
import org.example.repository.*;
import org.example.utils.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        BaseRepository.createTablesIfNotExist();

        Scanner scanner = new Scanner(System.in);
        BookRepository bookRepository = new BookRepository();
        ShopRepository shopRepository = new ShopRepository();
        UserRepository userRepository = new UserRepository();
        PurchaseRepository purchaseRepository = new PurchaseRepository();
        WishlistRepository wishlistRepository = new WishlistRepository();

        while (true) {
            System.out.println("1. Manage books");
            System.out.println("2. Manage shops");
            System.out.println("3. Manage users");
            System.out.println("4. Manage purchases");
            System.out.println("5. Manage wishlist");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    BookUtils.manageBooks(scanner, bookRepository);
                    break;
                case 2:
                    ShopUtils.manageShops(scanner, shopRepository);
                    break;
                case 3:
                    UserUtils.manageUsers(scanner, userRepository);
                    break;
                case 4:
                    PurchaseUtils.managePurchases(scanner, purchaseRepository);
                    break;
                case 5:
                    WishlistUtils.manageWishlist(scanner, wishlistRepository);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

