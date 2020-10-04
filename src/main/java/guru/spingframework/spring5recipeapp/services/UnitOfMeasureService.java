package guru.spingframework.spring5recipeapp.services;

import guru.spingframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.spingframework.spring5recipeapp.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasure> getUnitOfMeasures();

    UnitOfMeasure findById(Long id);

    UnitOfMeasureCommand findCommandById(Long id);

    UnitOfMeasureCommand saveRecipeCommand(UnitOfMeasureCommand recipeCommand);

    void deleteById(Long id);
}
