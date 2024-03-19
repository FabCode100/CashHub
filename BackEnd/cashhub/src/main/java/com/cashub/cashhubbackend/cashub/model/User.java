package com.cashub.cashhubbackend.cashub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Especifica o nome da tabela no banco de dados
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    // Construtores, getters e setters

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters e setters
    // ...
}
