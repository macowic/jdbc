package org.example.utils;

import org.example.models.Book;
import org.example.repository.BookRepository;
import org.example.repository.ShopRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookUtils {


    public static void manageBooks(Scanner scanner, BookRepository bookRepository) throws SQLException {
        while (true) {
            System.out.println("1. Add a book");
            System.out.println("2. View a book");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Get all books");
            System.out.println("6. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(scanner, bookRepository);
                    break;
                case 2:
                    viewBook(scanner, bookRepository);
                    break;
                case 3:
                    updateBook(scanner, bookRepository);
                    break;
                case 4:
                    deleteBook(scanner, bookRepository);
                    break;
                case 5:
                    getAllBooks(bookRepository);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void getAllBooks(BookRepository bookRepository) throws SQLException {
        List<Book> books = bookRepository.getAllBooks();
        for (Book book : books) {

            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Publisher: " + book.getPublisher());
            System.out.println("Year: " + book.getYear());
            System.out.println("Shop Name: " + (book.getShop() != null ? book.getShop().getName() : "N/A"));
            System.out.println("--------------------");
        }
    }

    private static void addBook(Scanner scanner, BookRepository bookRepository) throws SQLException {
        ShopRepository shopRepository = new ShopRepository();
        System.out.print("Enter book title: ");
        String title = scanner.next();
        System.out.print("Enter book author: ");
        String author = scanner.next();
        System.out.print("Enter book genre: ");
        String genre = scanner.next();
        System.out.println("Enter book publisher: ");
        String publisher = scanner.next();
        System.out.println("Enter book year: ");
        int year = scanner.nextInt();
        System.out.println("Enter book price: ");
        int price = scanner.nextInt();

        System.out.println("Available shops: ");
        shopRepository.getAllShops().forEach(shop -> System.out.println(shop.getId() + ". " + shop.getName()));
        System.out.print("Enter shop Name: ");

        String nameShop = scanner.next();


        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPublisher(publisher);
        book.setYear(year);
        book.setPrice(price);
        book.setShop(shopRepository.readShopByName(nameShop));

        bookRepository.createBook(book);
    }

    private static void viewBook(Scanner scanner, BookRepository bookRepository) throws SQLException {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();

        Book book = bookRepository.readBook(id);
        if (book != null) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());

        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updateBook(Scanner scanner, BookRepository bookRepository) throws SQLException {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();

        Book book = bookRepository.readBook(id);
        if (book != null) {
            System.out.print("Enter new title (leave blank to keep current): ");
            String title = scanner.next();
            if (!title.isEmpty()) {
                book.setTitle(title);
            }

            bookRepository.updateBook(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteBook(Scanner scanner, BookRepository bookRepository) throws SQLException {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();

        bookRepository.deleteBook(id);
    }

}
