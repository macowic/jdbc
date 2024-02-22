package org.example.models;


import java.util.List;

public class User {
    private int id;

    private int money;
    private String name;
    private String email;
    private boolean isAdmin;


    public User(int money, String name, String email, boolean isAdmin) {
        this.money = money;
        this.name = name;
        this.email = email;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean getIsAdmin() {
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

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    @Override
    public String toString() {
        return "User{" + "money=" + money + ", name=" + name + ", email=" + email + ", admin=" + isAdmin + '}';
    }

}

