package com.foodie.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.foodie.api.model.dto.DailyMealPlanDto;
import com.foodie.api.model.dto.NutritionIssueDto;
import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.model.dto.UserDto;
import com.foodie.api.model.entities.Recipe;
import com.foodie.api.repository.RecipeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateDailyMealPlanService {

  private final RecipeRepository recipeRepo;
  
  public DailyMealPlanDto createDailyMealPlan(UserDto user){
    DailyMealPlanDto plan = new DailyMealPlanDto();

    Integer calorieStatus = user.getRecommendedCalorieStatus();
    List<Long> userNutritionIssueIds = getUserNutritionIssueIds(user);
    RecipeDto breakfast = getFilteredRecipes(user, 0, calorieStatus, userNutritionIssueIds);
    RecipeDto lunch = getFilteredRecipes(user, 1, calorieStatus, userNutritionIssueIds);
    RecipeDto dinner = getFilteredRecipes(user, 2, calorieStatus, userNutritionIssueIds);

    plan = setDailyMealPlan(plan, breakfast, lunch, dinner);

    return plan;
  }

  private List<Long> getUserNutritionIssueIds(UserDto user) {
    List<Long> nutritionissueIds = new ArrayList<>();
    for (NutritionIssueDto nutritionIssue : user.getNutritionIssues()) {
      nutritionissueIds.add(nutritionIssue.getId());
    }
    return nutritionissueIds;
  }

  private DailyMealPlanDto setDailyMealPlan(DailyMealPlanDto plan, RecipeDto breakfast, RecipeDto lunch,
      RecipeDto dinner) {
    plan.setBreakfast(breakfast);
    plan.setLunch(lunch);
    plan.setDinner(dinner);
    return plan;
  }

  public RecipeDto getFilteredRecipes(UserDto user, Integer recipeType, Integer calorieStatus, List<Long> userNutritionIssueIds){
    Optional<Recipe> recipe = recipeRepo.findFilteredRecipes(recipeType, calorieStatus, userNutritionIssueIds);
    if(recipe.isPresent()) return RecipeService.toPayload(recipe.get());
    throw new RuntimeException("Database does not contain recipe with filtered preferences!");
  }
}
