package com.example.sfgtrainingrecipe.controllers;

import com.example.sfgtrainingrecipe.repositories.CategoryRepository;
import com.example.sfgtrainingrecipe.repositories.UnitOfMeasureRepository;
import com.example.sfgtrainingrecipe.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeServiceImpl recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeServiceImpl recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/index"})
    public String getIndexPage(Model model){
        log.debug("displaying recipes");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
