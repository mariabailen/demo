package com.mariabailen.simfood.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {


    public Ingredient() {
    }

    public Ingredient(String name, String quantity, Receipt receipt) {
        this.name = name;
        this.quantity = quantity;
        this.receipt = receipt;
    }

    public Ingredient(Long id, String name, String quantity, Receipt receipt) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.receipt = receipt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
