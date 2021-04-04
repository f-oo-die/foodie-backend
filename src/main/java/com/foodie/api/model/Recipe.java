package com.foodie.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Recipe {
  private Long id;
  private String title;
  private String preparation;
  private Integer numOfCalories;
  private Integer typeOfMeal;
}
