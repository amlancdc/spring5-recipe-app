package guru.spingframework.spring5recipeapp.services;

import guru.spingframework.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
