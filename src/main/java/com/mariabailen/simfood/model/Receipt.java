package com.mariabailen.simfood.model;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "chef_id", nullable = false)
    private Chef chef;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReceiptImage> images;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public List<Ingredient> getIngredientes() {
        return ingredients;
    }

    public void setIngredientes(List<Ingredient> ingredientes) {
        this.ingredients = ingredientes;
    }

    public List<ReceiptImage> getImagenes() {
        return images;
    }

    public void setImagenes(List<ReceiptImage> imagenes) {
        this.images = imagenes;
    }
}
