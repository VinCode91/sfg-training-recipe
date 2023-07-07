package com.example.sfgtrainingrecipe.controllers;

import com.example.sfgtrainingrecipe.commands.RecipeCommand;
import com.example.sfgtrainingrecipe.domain.Recipe;
import com.example.sfgtrainingrecipe.exceptions.NotFoundException;
import com.example.sfgtrainingrecipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.example.sfgtrainingrecipe.controllers.ControllerExceptionHandler.handleException;

/**
 * Created by jt on 6/19/17.
 */
@Controller
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}/show")
    public String getRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }

    @PostMapping
    public String create(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute RecipeCommand updatedCommand) {
        if(recipeService.findById(Long.valueOf(id)) != null) {
            RecipeCommand saved = recipeService.saveRecipeCommand(updatedCommand);
            return "redirect:/recipe/" + saved.getId() + "/show";
        } else {
            return "redirect:/recipe/new";
        }
    }

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        log.debug("Deleting recipe id: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(NotFoundException exception) {
        log.error("Handling not found exception");
        return handleException(exception, "404error");
    }


}
