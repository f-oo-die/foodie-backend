package com.foodie.api.builder;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.model.entities.Ingredient;

public class ObjectTestBuilder {
  private static final String INGREDIENT_NAME = "Potato";

  public static final long INGREDIENT_ID = 1L;

  private ObjectTestBuilder() {
    throw new UnsupportedOperationException();
  }

  public static Ingredient buildIngredient() {
    Ingredient ingredient = new Ingredient();
    ingredient.setId(INGREDIENT_ID);
    ingredient.setIngredientName(INGREDIENT_NAME);
    return ingredient;
  }

  public static IngredientDto buildIngredientDto() {
    IngredientDto ingredient = new IngredientDto();
    ingredient.setId(INGREDIENT_ID);
    ingredient.setIngredientName(INGREDIENT_NAME);
    return ingredient;
  }

}
