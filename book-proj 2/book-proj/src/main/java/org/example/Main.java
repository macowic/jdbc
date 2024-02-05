package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.example.models.Book;
import org.example.models.Shop;
import org.example.repository.BaseRepository;
import org.example.repository.BookRepository;
import org.example.repository.ShopRepository;
import org.example.repository.UserRepository;
import org.example.utils.BookUtils;
import org.example.utils.ShopUtils;
import org.example.utils.UserUtils;

public class Main {
    public static void main(String[] args) throws SQLException {
        BaseRepository.createTablesIfNotExist();

        Scanner scanner = new Scanner(System.in);
        BookRepository bookRepository = new BookRepository();
        ShopRepository shopRepository = new ShopRepository();
        UserRepository userRepository = new UserRepository();

        while (true) {
            System.out.println("1. Manage books");
            System.out.println("2. Manage shops");
            System.out.println("3. Manage users");
            System.out.println("4. Exit");
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
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}