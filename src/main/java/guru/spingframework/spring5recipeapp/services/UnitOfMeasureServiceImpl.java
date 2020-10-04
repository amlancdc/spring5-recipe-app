package guru.spingframework.spring5recipeapp.services;

import guru.spingframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.spingframework.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.spingframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.spingframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.spingframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand, UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Override
    public Set<UnitOfMeasure> getUnitOfMeasures() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        unitOfMeasureRepository.findAll().iterator().forEachRemaining(unitOfMeasures :: add);

        return unitOfMeasures;
    }

    @Override
    public UnitOfMeasure findById(Long id) {
        return unitOfMeasureRepository.findById(id).orElseThrow(RuntimeException :: new);
    }

    @Override
    public UnitOfMeasureCommand findCommandById(Long id) {
        return unitOfMeasureToUnitOfMeasureCommand.convert(findById(id));
    }

    @Override
    public UnitOfMeasureCommand saveRecipeCommand(UnitOfMeasureCommand unitOfMeasureCommand) {
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.save(unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand));

        return unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);
    }

    @Override
    public void deleteById(Long id) {
        unitOfMeasureRepository.deleteById(id);
    }
}
