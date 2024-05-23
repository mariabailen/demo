package com.mariabailen.simfood.model;

import jakarta.persistence.*;

@Entity
@Table(name = "appUser")
public class User {

    

    public User() {
    }

    public User(String username, String pass, String role) {
        this.username = username;
        this.pass = pass;
        this.role = role;
    }

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "role", nullable = false)
    private String role;

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
