package com.foodie.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {
  private Long id;
  private String title;
  private String preparation;
  private Integer numOfCalories;
  private Integer typeOfMeal;
}