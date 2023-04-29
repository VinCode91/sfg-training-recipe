package com.example.sfgtrainingrecipe.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient() {}



    public Ingredient(String description, double amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
        this.description = description;
        this.amount = new BigDecimal(amount);
        this.unitOfMeasure = unitOfMeasure;
        this.recipe = recipe;
    }

    public Ingredient(String description, double amount, UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = new BigDecimal(amount);
        this.unitOfMeasure = unitOfMeasure;
    }
}
