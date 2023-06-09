package com.example.sfgtrainingrecipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;
    @ManyToOne(fetch = FetchType.EAGER)
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
