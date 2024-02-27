package org.example.models;


import java.util.List;

public class User {
    private int id;
    private int money;
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;


    public User(int money, String name, String email, String password, boolean isAdmin) {
        this.money = money;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
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

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
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
    public void setPassword(String password) {
        this.password = password;
    }


    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    @Override
    public String toString() {
        return "User{" + "money=" + money + ", name=" + name + ", email=" + email + ", admin=" + isAdmin + '}';
    }

}

