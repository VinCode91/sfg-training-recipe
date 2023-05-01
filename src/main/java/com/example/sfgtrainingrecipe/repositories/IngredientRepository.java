package com.example.sfgtrainingrecipe.repositories;

import com.example.sfgtrainingrecipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
