package guru.spingframework.spring5recipeapp.services;

import guru.spingframework.spring5recipeapp.commands.IngredientCommand;
import guru.spingframework.spring5recipeapp.domain.Ingredient;

import java.util.Set;

public interface IngredientService {

    Set<Ingredient> getIngredients();

    Ingredient findById(Long id);

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand findCommandById(Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
