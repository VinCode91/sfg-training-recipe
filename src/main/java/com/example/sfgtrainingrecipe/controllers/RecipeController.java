package com.example.sfgtrainingrecipe.controllers;

import com.example.sfgtrainingrecipe.commands.RecipeCommand;
import com.example.sfgtrainingrecipe.domain.Recipe;
import com.example.sfgtrainingrecipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jt on 6/19/17.
 */
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/{id}/show")
    public String getRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @RequestMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping
    public String create(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @PutMapping
    @RequestMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute RecipeCommand updatedCommand) {
        assert recipeService.findById(Long.valueOf(id)) != null;
        RecipeCommand saved = recipeService.saveRecipeCommand(updatedCommand);
        return "redirect:/recipe/" + saved.getId() + "/show";
    }
}
