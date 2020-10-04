package guru.spingframework.spring5recipeapp.services;

import guru.spingframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.spingframework.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.spingframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.spingframework.spring5recipeapp.domain.UnitOfMeasure;
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

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Mock
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,
                unitOfMeasureToUnitOfMeasureCommand, unitOfMeasureCommandToUnitOfMeasure);
    }

    @Test
    void getUnitOfMeasures() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);

        Set<UnitOfMeasure> unitOfMeasuresExpected = new HashSet<>();
        unitOfMeasuresExpected.add(unitOfMeasure);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasuresExpected);

        Set<UnitOfMeasure> unitOfMeasuresActual = unitOfMeasureService.getUnitOfMeasures();

        verify(unitOfMeasureRepository, times(1)).findAll();
        assertEquals(unitOfMeasuresExpected, unitOfMeasuresActual);
    }

    @Test
    void findById() {
        UnitOfMeasure unitOfMeasureExpected = new UnitOfMeasure();
        unitOfMeasureExpected.setId(1L);
        Optional<UnitOfMeasure> unitOfMeasureExpectedOptional = Optional.of(unitOfMeasureExpected);

        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(unitOfMeasureExpectedOptional);

        UnitOfMeasure unitOfMeasureActual = unitOfMeasureService.findById(1L);

        verify(unitOfMeasureRepository, times(1)).findById(anyLong());

        assertEquals(unitOfMeasureExpected, unitOfMeasureActual);
    }

    @Test
    void findCommandById() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        Optional<UnitOfMeasure> unitOfMeasureOptional = Optional.of(unitOfMeasure);
        UnitOfMeasureCommand unitOfMeasureCommandExpected = new UnitOfMeasureCommand();
        unitOfMeasureCommandExpected.setId(unitOfMeasure.getId());

        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(unitOfMeasureOptional);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(any())).thenReturn(unitOfMeasureCommandExpected);

        UnitOfMeasureCommand unitOfMeasureCommandActual = unitOfMeasureService.findCommandById(1L);

        verify(unitOfMeasureRepository, times(1)).findById(anyLong());
        verify(unitOfMeasureToUnitOfMeasureCommand, times(1)).convert(any());

        assertEquals(unitOfMeasureCommandExpected, unitOfMeasureCommandActual);
    }

    @Test
    void saveUnitOfMeasureCommandCommand() {
        UnitOfMeasureCommand unitOfMeasureCommandExpected = new UnitOfMeasureCommand();
        unitOfMeasureCommandExpected.setId(1L);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(unitOfMeasureCommandExpected.getId());

        when(unitOfMeasureToUnitOfMeasureCommand.convert(any())).thenReturn(unitOfMeasureCommandExpected);
        when(unitOfMeasureRepository.save(any())).thenReturn(unitOfMeasure);

        UnitOfMeasureCommand unitOfMeasureCommandActual = unitOfMeasureService.saveRecipeCommand(unitOfMeasureCommandExpected);

        verify(unitOfMeasureToUnitOfMeasureCommand, times(1)).convert(any());
        verify(unitOfMeasureRepository, times(1)).save(any());

        assertEquals(unitOfMeasureCommandExpected, unitOfMeasureCommandActual);
    }

    @Test
    void deleteById() {
        unitOfMeasureService.deleteById(1L);

        verify(unitOfMeasureRepository, times(1)).deleteById(anyLong());
    }
}