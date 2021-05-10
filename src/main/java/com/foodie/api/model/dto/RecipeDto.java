package com.foodie.api.model.dto;

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
  private Set<IngredientListDto> ingredientList;
  private Set<FavoriteRecipeDto> favoriteRecipes;
  private Set<DailyMealPlanDto> breakfastSet;
  private Set<DailyMealPlanDto> lunchSet;
  private Set<DailyMealPlanDto> dinnerSet;
  private Set<CounterDto> recipeCount;
  private Set<PreventionListDto> preventionList;
}
