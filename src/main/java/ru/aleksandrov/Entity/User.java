package ru.aleksandrov.Entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int userId;
    private String name;
    private String login;
    private String password;
    private String email;
    private Role role;
    private Set<Word> words = new HashSet<>(0);

    public User() {
    }

    public User(int userId, String name, String login, String password, String email, Role role) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
