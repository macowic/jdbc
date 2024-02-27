package org.example.utils;

import org.example.Main;
import org.example.models.Book;
import org.example.models.User;
import org.example.repository.BookRepository;
import org.example.repository.PurchaseRepository;
import org.example.repository.UserRepository;
import org.example.repository.WishlistRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.example.Main.currentUser;

public class UserUtils {
    public static void manageUsers(Scanner scanner, UserRepository userRepository) throws SQLException {
        while (true) {
            System.out.println("1. Add a user");
            System.out.println("2. View a user");
            System.out.println("3. Update a user");
            System.out.println("4. Delete a user");
            System.out.println("5. Get all users");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addUser(scanner, userRepository);
                    break;
                case 2:
                    viewUser(scanner, userRepository);
                    break;
                case 3:
                    updateUser(scanner, userRepository);
                    break;
                case 4:
                    deleteUser(scanner, userRepository);
                    break;
                case 5:
                    getAllUsers(userRepository);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void getAllUsers(UserRepository userRepository) throws SQLException {
        List<User> users = userRepository.getAllUsers();
        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Money: " + user.getMoney());
            System.out.println("--------------------");
        }
    }

    private static void addUser(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.print("Enter user name: ");
        String name = scanner.next();
        System.out.print("Enter user email: ");
        String email = scanner.next();
        System.out.print("Enter user money: ");
        int money = scanner.nextInt();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMoney(money);

        userRepository.createUser(user);
    }

    private static void viewUser(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.print("Enter user email: ");
        String email = scanner.next();

        User user = userRepository.getUserByEmail(email);
        if (user != null) {
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Money: " + user.getMoney());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUser(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.print("Enter user email: ");
        String mail = scanner.next();

        User user = userRepository.getUserByEmail(mail);
        if (user != null) {
            System.out.print("Enter new name (leave blank to keep current): ");
            String name = scanner.next();
            if (!name.isEmpty()) {
                user.setName(name);
            }
            System.out.print("Enter new email (leave blank to keep current): ");
            String email = scanner.next();
            if (!email.isEmpty()) {
                user.setEmail(email);
            }
            System.out.print("Enter new money (leave blank to keep current): ");
            String money = scanner.next();
            if (!money.isEmpty()) {
                user.setMoney(Integer.parseInt(money));
            }

            userRepository.updateUser(user);
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deleteUser(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();

        userRepository.deleteUser(id);
    }

    public static void viewProfile(UserRepository userRepository, PurchaseRepository purchaseRepository) throws SQLException {
        User user = userRepository.getUserByEmail(currentUser.getEmail());
        if (user != null) {
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Money: " + user.getMoney());
            List<Book> books = purchaseRepository.getAllBooksByUser(user.getName());

            for (Book book : books) {
                System.out.println("Book ID: " + book.getId() + ", Book Title: " + book.getTitle());
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public static User signInOrSignUp(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.println("1. Sign In");
        System.out.println("2. Sign Up");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return signIn(scanner, userRepository);
            case 2:
                return signUp(scanner, userRepository);
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return signInOrSignUp(scanner, userRepository);
        }
        return null;
    }

    private static User signIn(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        User user = userRepository.getUserByEmailAndPassword(email, password);
        if (user == null) {
            System.out.println("Invalid email or password. Please try again.");
            return signIn(scanner, userRepository);
        } else {
            return user;
        }
    }

    private static User signUp(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter money: ");
        int money = scanner.nextInt();
        System.out.print("Are you an admin? (true/false): ");
        boolean isAdmin = scanner.nextBoolean();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setMoney(money);
        user.setAdmin(isAdmin);
        userRepository.createUser(user);
        return user;
    }

    public static void buyBook(Scanner scanner, PurchaseRepository purchaseRepository) throws SQLException {
        System.out.print("Enter book ID: ");
        String title = scanner.next();

        purchaseRepository.createPurchase(Main.currentUser.getId(), title);
        System.out.println("Book purchased successfully!");
    }

    public static void addToWishlist(Scanner scanner, WishlistRepository wishlistRepository, BookRepository bookRepository) throws SQLException {

        List<Book> books = bookRepository.getAllBooks();
        for (Book book : books) {
            System.out.println(book.getId() + ", Book Title: " + book.getTitle());
        }
        System.out.print("Enter book ID: ");

        int bookId = scanner.nextInt();

        wishlistRepository.addToWishlist(Main.currentUser.getId(), bookId);
        System.out.println("Book added to wishlist successfully!");
    }

    public static void manageUser(Scanner scanner, BookRepository bookRepository, UserRepository userRepository, PurchaseRepository purchaseRepository, WishlistRepository wishlistRepository) throws SQLException {
        while (true) {
            System.out.println("1. View profile");
            System.out.println("2. Buy a book");
            System.out.println("3. Add a book to wishlist");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProfile(userRepository, purchaseRepository);
                    break;
                case 2:
                    buyBook(scanner, purchaseRepository);
                    break;
                case 3:
                    addToWishlist(scanner, wishlistRepository, bookRepository);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

