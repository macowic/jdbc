package org.example.utils;

import org.example.models.User;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();

        User user = userRepository.readUser(id);
        if (user != null) {
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Money: " + user.getMoney());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUser(Scanner scanner, UserRepository userRepository) throws SQLException {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();

        User user = userRepository.readUser(id);
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

}
