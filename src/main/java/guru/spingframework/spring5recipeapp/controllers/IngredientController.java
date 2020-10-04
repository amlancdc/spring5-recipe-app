package guru.spingframework.spring5recipeapp.controllers;

import guru.spingframework.spring5recipeapp.commands.IngredientCommand;
import guru.spingframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.spingframework.spring5recipeapp.domain.Ingredient;
import guru.spingframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import guru.spingframework.spring5recipeapp.services.IngredientService;
import guru.spingframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model){
        log.debug("Getting Ingredient list for recipe id: " + id);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showByRecipeIdAndIngredientID(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        log.debug("showById id: " + ingredientId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureRepository.findAll());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String addIngredient(@PathVariable String recipeId, Model model){
        Ingredient ingredient = new Ingredient();
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", unitOfMeasureRepository.findAll());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@PathVariable String recipeId, @ModelAttribute IngredientCommand command){
        command.setRecipeId(Long.valueOf(recipeId));
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String delete(@PathVariable String recipeId, @PathVariable String ingredientId) {
        log.debug("Deleting id: " + ingredientId);

        ingredientService.deleteByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return  "redirect:/recipe/{recipeId}/ingredients";
    }
}
