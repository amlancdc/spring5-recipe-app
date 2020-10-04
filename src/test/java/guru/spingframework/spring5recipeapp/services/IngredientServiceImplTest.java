package guru.spingframework.spring5recipeapp.services;

import guru.spingframework.spring5recipeapp.commands.IngredientCommand;
import guru.spingframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import guru.spingframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import guru.spingframework.spring5recipeapp.domain.Ingredient;
import guru.spingframework.spring5recipeapp.domain.Recipe;
import guru.spingframework.spring5recipeapp.repositories.IngredientRepository;
import guru.spingframework.spring5recipeapp.repositories.RecipeRepository;
import guru.spingframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    void getIngredients() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        Set<Ingredient> ingredientData = new HashSet<>();
        ingredientData.add(ingredient);

        when(ingredientRepository.findAll()).thenReturn(ingredientData);

        Set<Ingredient> ingredients = ingredientService.getIngredients();

        assertEquals(1, ingredients.size());
        verify(ingredientRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        Optional<Ingredient> ingredientOptionalExpected = Optional.of(ingredient);

        when(ingredientRepository.findById(anyLong())).thenReturn(ingredientOptionalExpected);

        Ingredient ingredientOptionalActual = ingredientService.findById(2L);

        assertEquals(ingredientOptionalExpected, ingredientOptionalExpected);
        verify(ingredientRepository, times(1)).findById(anyLong());
    }

    @Test
    void findByRecipeIdAndId() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);
        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(ingredient);

        IngredientCommand ingredientCommandExpected = new IngredientCommand();
        ingredientCommandExpected.setId(ingredient.getId());

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setIngredients(ingredientSet);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        when(ingredientToIngredientCommand.convert(any())).thenReturn(ingredientCommandExpected);

        IngredientCommand ingredientCommandActual = ingredientService.findByRecipeIdAndIngredientId(1L, 2L);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(ingredientToIngredientCommand, times(1)).convert(any());

        assertEquals(ingredientCommandExpected, ingredientCommandActual);
    }

    @Test
    void saveIngredientCommand() {
        Ingredient ingredient = new Ingredient();
        Set<Ingredient> ingredientData = new HashSet();
        ingredientData.add(ingredient);
        when(ingredientRepository.findAll()).thenReturn(ingredientData);

        Set<Ingredient> ingredients = ingredientService.getIngredients();

        assertEquals(1, ingredients.size());
        verify(ingredientRepository, times(1)).findAll();
    }

//    @Test
//    void deleteRecipeIdAndIngredientId() {
//        Reci
//        ingredientService.deleteRecipeIdAndIngredientId(idToDelete);
//
//        verify(ingredientRepository, times(1)).deleteById(anyLong());
//    }

}