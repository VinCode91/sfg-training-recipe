package com.example.sfgtrainingrecipe.controllers;

import com.example.sfgtrainingrecipe.domain.Recipe;
import com.example.sfgtrainingrecipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/show/{id}")
    public String getRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }
}
