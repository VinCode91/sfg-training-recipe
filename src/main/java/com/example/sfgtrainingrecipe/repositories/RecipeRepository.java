package com.example.sfgtrainingrecipe.repositories;

import com.example.sfgtrainingrecipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
