package org.example.models;


import java.util.List;

public class User {
    private int id;

    private int money;
    private String name;
    private String email;
    private List<Book> books;


    public User(int money, String name, String email, List<Book> books) {
        this.money = money;
        this.name = name;
        this.email = email;
        this.books = books;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "User{" + "money=" + money + ", name=" + name + ", email=" + email + ", books=" + books + '}';
    }

}

