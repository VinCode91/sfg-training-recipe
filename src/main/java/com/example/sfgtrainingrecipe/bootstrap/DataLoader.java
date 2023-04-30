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
        UnitOfMeasure teaspoonUom = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tableSpoonUom = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure eachUom = unitOfMeasureRepository.findByDescription("Each").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();
        UnitOfMeasure slice = unitOfMeasureRepository.findByDescription("slice").get();
        UnitOfMeasure cupsUom = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pintUom = unitOfMeasureRepository.findByDescription("Pint").get();


        Category mexican = categoryRepository.findByDescription("Mexican").get();
        Category american = categoryRepository.findByDescription("American").get();


        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Guacamole: A classic Mexican Dish");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(0);
        recipe1.setServings(4);
        recipe1.setSource("Simply Recipes");
        recipe1.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe1.setDifficulty(Difficulty.EASY);

        recipe1.getCategories().add(mexican);
        log.debug("Create new recipe");


        Notes note1 = new Notes();
        note1.setRecipeNotes("1. Be careful handling chilis! If using, it's best to wear food-safe gloves. " +
                "If no gloves are available, wash your hands thoroughly after handling, and do not touch your " +
                "eyes or the area near your eyes for several hours afterwards.\n" +
                "2. Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");
        //note1.setRecipe(recipe1); // Necessary for bidirectional relation
        recipe1.setNotes(note1); // can refactor this setter to update recipe for Notes param, then remove previous line (better code)
        log.debug("load recipe notes");

        recipe1.getIngredients().addAll(Arrays.asList(new Ingredient("ripe avocados", 2, eachUom, recipe1),
                new Ingredient("kosher salt", 0.25, teaspoonUom, recipe1),
                new Ingredient("fresh lime", 1, tableSpoonUom, recipe1),
                new Ingredient("minced red onion", 3, tableSpoonUom, recipe1),
                new Ingredient("serrano chilis", 2, eachUom, recipe1),
                new Ingredient("cilantro", 2, tableSpoonUom, recipe1),
                new Ingredient("black pepper", 1, pinch, recipe1),
                new Ingredient("ripe tomato", 0.5, eachUom, recipe1),
                new Ingredient("Red radish or jicama", 12, slice, recipe1)
        ));
        recipeRepository.save(recipe1);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", 2, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", 1, teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", 1, teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", 1, teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", .5, teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", 1, eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", 1, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", 3, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", 2, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", 4, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", 8, eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", 3, cupsUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", 2, eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", 4, eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", .5, pintUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", .25, eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", 4, eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", 4, cupsUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", 4, eachUom));

        tacosRecipe.getCategories().add(american);
        tacosRecipe.getCategories().add(mexican);

        tacosRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setServings(4);
        tacosRecipe.setSource("Simply Recipes");

        recipeRepository.save(tacosRecipe);
        
        log.debug("Save recipes with their ingredients");
    }
}
