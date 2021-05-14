package com.foodie.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientListDto {
  private Long id;
  private Double amount;
  private String amountLabel;
  private IngredientDto ingredient;
  private RecipeDto recipe;
}
