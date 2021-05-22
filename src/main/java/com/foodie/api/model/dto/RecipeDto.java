package com.foodie.api.model.dto;

import java.util.List;
import java.util.Set;

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
  private List<IngredientListDto> ingredientList;
  private Set<DailyMealPlanDto> dailyMealPlan;
}
