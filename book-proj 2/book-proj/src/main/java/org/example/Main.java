package org.example;

import java.sql.SQLException;
import java.util.Scanner;

import org.example.models.Book;
import org.example.models.Shop;
import org.example.models.User;
import org.example.repository.*;
import org.example.utils.*;

import static org.example.utils.UserUtils.signInOrSignUp;

public class Main {
    public static User currentUser;

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello reader!");
        System.out.println("Here you can buy any book that you want.");
        System.out.println("Sign up/in here");
        BaseRepository.createTablesIfNotExist();

        Scanner scanner = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        currentUser = signInOrSignUp(scanner, userRepository);

        while (currentUser != null) {
            if (currentUser.isAdmin()) {
                // Admin functionalities
                BookRepository bookRepository = new BookRepository();
                ShopRepository shopRepository = new ShopRepository();
                PurchaseRepository purchaseRepository = new PurchaseRepository();
                WishlistRepository wishlistRepository = new WishlistRepository();
                System.out.println("1. Manage books");
                System.out.println("2. Manage shops");
                System.out.println("3. Manage users");
                System.out.println("4. Manage purchases");
                System.out.println("5. Manage wishlist");
                System.out.println("6. Sign out");
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
                        currentUser = null;
                        signInOrSignUp(scanner, userRepository);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("1. Buy a book");
                System.out.println("2. Add a book to wishlist");
                System.out.println("3. View profile");
                System.out.println("4. Sign out");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        UserUtils.buyBook(scanner, new PurchaseRepository());
                        break;
                    case 2:
                        UserUtils.addToWishlist(scanner, new WishlistRepository(), new BookRepository());
                        break;
                    case 3:
                        UserUtils.viewProfile(userRepository, new PurchaseRepository());
                        break;
                    case 4:
                        currentUser = null;
                        signInOrSignUp(scanner, userRepository);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}



