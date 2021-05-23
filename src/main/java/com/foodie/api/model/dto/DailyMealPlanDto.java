package com.foodie.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMealPlanDto {
  private Long id;
  private String dateId;
  private Integer rating;
  private RecipeDto breakfast;
  private RecipeDto lunch;
  private RecipeDto dinner;
  private UserDto user;
}
