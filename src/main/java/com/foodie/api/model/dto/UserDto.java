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
public class UserDto {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  // private Integer weight;
  // private Integer height;
  // private String gender;
  private Set<ShoppingListDto> shoppingLists;
  // private Set<NutritionIssueListDto> nutritionIssueLists;
  // private Set<FavoriteRecipeDto> favoriteRecipes;
  // private Set<DailyMealPlanDto> dailyMealPlans;
  // private Set<CounterDto> recipeCount;
}
