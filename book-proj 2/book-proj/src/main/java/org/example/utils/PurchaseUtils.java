package org.example.utils;

import org.example.models.Book;
import org.example.models.Purchase;
import org.example.repository.PurchaseRepository;
import org.example.repository.UserRepository;
import org.example.repository.BookRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PurchaseUtils {

    public static void managePurchases(Scanner scanner, PurchaseRepository purchaseRepository) throws SQLException {
        while (true) {
            System.out.println("1. Make a purchase");
            System.out.println("2. View all purchases");
            System.out.println("3. View all books by a user");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makePurchase(scanner, purchaseRepository);
                    break;
                case 2:
                    viewAllPurchases(purchaseRepository);
                    break;
                case 3:
                    viewAllBooksByUser(scanner, purchaseRepository);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makePurchase(Scanner scanner, PurchaseRepository purchaseRepository) throws SQLException {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter book ID: ");
        String title = scanner.next();

        purchaseRepository.createPurchase(userId, title);
        System.out.println("Purchase made successfully!");
    }

    private static void viewAllPurchases(PurchaseRepository purchaseRepository) throws SQLException {
        List<Purchase> purchases = purchaseRepository.getAllPurchases();

        for (Purchase purchase : purchases) {
            System.out.println("User name: " + purchase.getUser().getName() + ", Book name: " + purchase.getBook().getTitle());
        }
    }

    private static void viewAllBooksByUser(Scanner scanner, PurchaseRepository purchaseRepository) throws SQLException {
        System.out.print("Enter user name: ");
        String userName = scanner.next();

        List<Book> books = purchaseRepository.getAllBooksByUser(userName);

        for (Book book : books) {
            System.out.println("Book ID: " + book.getId() + ", Book Title: " + book.getTitle());
        }
    }
}

