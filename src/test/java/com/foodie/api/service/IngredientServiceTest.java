package com.foodie.api.service;

import static com.foodie.api.builder.ObjectTestBuilder.INGREDIENT_ID;
import static com.foodie.api.builder.ObjectTestBuilder.buildIngredient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.model.entities.Ingredient;
import com.foodie.api.repository.IngredientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class IngredientServiceTest {
  private IngredientRepository ingredientRepository;
  private IngredientService ingredientService;

  @BeforeEach
  public void before() {
    ingredientRepository = Mockito.mock(IngredientRepository.class);
    ingredientService = new IngredientService(ingredientRepository);
  }

  @Test
  public void getAllIngredientsSuccess() {
    Ingredient dbIngredient = buildIngredient();
    List<Ingredient> ingredientList = new ArrayList<Ingredient>();
    ingredientList.add(dbIngredient);
    when(ingredientRepository.findAll())
        .thenReturn(ingredientList);

    Collection<IngredientDto> ingredients = ingredientService.getAll();

    assertNotNull(ingredients);
  }

  @Test
  public void getSingleIngredientSuccess() {
    Ingredient dbIngredient = buildIngredient();
    when(ingredientRepository.findById(INGREDIENT_ID))
        .thenReturn(Optional.of(dbIngredient));

    IngredientDto ingredient = ingredientService.getIngredient(INGREDIENT_ID);

    assertNotNull(ingredient);
    assertEquals(dbIngredient.getId(), ingredient.getId());
    assertEquals(dbIngredient.getIngredientName(), ingredient.getIngredientName());
  }

  @Test
  public void getSingleAIngredientNotFound() {
    when(ingredientRepository.findById(INGREDIENT_ID))
        .thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> ingredientService.getIngredient(INGREDIENT_ID));
  }

  // @Test
  // public void saveRecipeSuccess() {
  //   Ingredient dbIngredient = buildIngredient();
  //   when(ingredientRepository.save(dbIngredient))
  //       .thenReturn(dbIngredient);

  //   IngredientDto ingredient = ingredientService.getIngredient(dbIngredient.getId());
    
  //   assertNotNull(ingredient);
  //   assertEquals(dbIngredient.getId(), ingredient.getId());
  //   assertEquals(dbIngredient.getIngredientName(), ingredient.getIngredientName());
  // }

}
