package com.example.sfgtrainingrecipe.bootstrap;

import com.example.sfgtrainingrecipe.domain.*;
import com.example.sfgtrainingrecipe.repositories.CategoryRepository;
import com.example.sfgtrainingrecipe.repositories.RecipeRepository;
import com.example.sfgtrainingrecipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (recipeRepository.count() == 0)
            loadData();
    }

    private void loadData() {
        log.debug("Adding new units of measure");
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure piece = unitOfMeasureRepository.findByDescription("Piece").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure slice = unitOfMeasureRepository.findByDescription("slice").get();


        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Guacamole: A classic Mexican Dish");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(0);
        recipe1.setServings(4);
        recipe1.setSource("Simply Recipes");
        recipe1.setDirections("1. Cut avocados\n2. Mash avocado flesh\n3. Add remaining ingredients to taste\n4. Serve immediately");
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe1.setDifficulty(Difficulty.EASY);

        recipe1.getCategories().add(categoryRepository.findByDescription("Mexican").get());
        log.debug("Create new recipe");


        Notes note1 = new Notes();
        note1.setRecipeNotes("1. Be careful handling chilis! If using, it's best to wear food-safe gloves. " +
                "If no gloves are available, wash your hands thoroughly after handling, and do not touch your " +
                "eyes or the area near your eyes for several hours afterwards.\n" +
                "2. Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");
        //note1.setRecipe(recipe1); // Necessary for bidirectional relation
        recipe1.setNotes(note1); // can refactor this setter to update recipe for Notes param, then remove previous line (better code)
        log.debug("load recipe notes");

        recipe1.getIngredients().addAll(Arrays.asList(new Ingredient("ripe avocados", 2, piece, recipe1),
                new Ingredient("kosher salt", 0.25, teaspoon, recipe1),
                new Ingredient("fresh lime", 1, tablespoon, recipe1),
                new Ingredient("minced red onion", 3, tablespoon, recipe1),
                new Ingredient("serrano chilis", 2, piece, recipe1),
                new Ingredient("cilantro", 2, tablespoon, recipe1),
                new Ingredient("black pepper", 1, pinch, recipe1),
                new Ingredient("ripe tomato", 0.5, piece, recipe1),
                new Ingredient("Red radish or jicama", 12, slice, recipe1)
        ));
        recipeRepository.save(recipe1);
        log.debug("Save recipe with ingredients");
    }
}
