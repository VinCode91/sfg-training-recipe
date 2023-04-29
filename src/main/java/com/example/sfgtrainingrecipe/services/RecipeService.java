package com.example.sfgtrainingrecipe.services;

import com.example.sfgtrainingrecipe.commands.RecipeCommand;
import com.example.sfgtrainingrecipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand findCommandById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long id);
}
