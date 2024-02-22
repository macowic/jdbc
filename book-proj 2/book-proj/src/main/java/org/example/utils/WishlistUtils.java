package org.example.utils;

import org.example.models.Book;
import org.example.repository.WishlistRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class WishlistUtils {

    public static void manageWishlist(Scanner scanner, WishlistRepository wishlistRepository) throws SQLException {
        while (true) {
            System.out.println("1. Add a book to wishlist");
            System.out.println("2. View all books in wishlist");
            System.out.println("3. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addToWishlist(scanner, wishlistRepository);
                    break;
                case 2:
                    viewAllBooksInWishlist(scanner, wishlistRepository);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addToWishlist(Scanner scanner, WishlistRepository wishlistRepository) throws SQLException {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();

        wishlistRepository.addToWishlist(userId, bookId);
        System.out.println("Book added to wishlist successfully!");
    }

    private static void viewAllBooksInWishlist(Scanner scanner, WishlistRepository wishlistRepository) throws SQLException {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();

        List<Book> books = wishlistRepository.getAllBooksInWishlist(userId);

        for (Book book : books) {
            System.out.println("Book ID: " + book.getId() + ", Book Title: " + book.getTitle());
        }
    }
}

