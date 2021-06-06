package com.foodie.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    Integer calorieStatus = getRecommendedCalorieStatus(user);
    List<Long> userNutritionIssueIds = getUserNutritionIssueIds(user);
    List<RecipeDto> breakfastList = getRecipesByTypeAndStatus(0, calorieStatus, userNutritionIssueIds);
    List<RecipeDto> lunchList = getRecipesByTypeAndStatus(1, calorieStatus, userNutritionIssueIds);
    List<RecipeDto> dinnerList = getRecipesByTypeAndStatus(2, calorieStatus, userNutritionIssueIds);

    RecipeDto breakfast = chooseMeal(breakfastList, user);
    RecipeDto lunch = chooseMeal(lunchList, user);
    RecipeDto dinner = chooseMeal(dinnerList, user);

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

  private Integer getRecommendedCalorieStatus(UserDto user) {
    double BMI = getBMI(user.getWeight(), user.getHeight());
    if (BMI > 25) return 2;
    else if (BMI > 18.5 && BMI < 24.9) return 1;
    return 0;
  }

  private double getBMI(Integer weight, Integer height){
    return Double.valueOf(weight) / (Math.pow(height / 10, 2)) * 100;
  }

  public List<RecipeDto> getRecipesByTypeAndStatus(Integer recipeType, Integer calorieStatus, List<Long> userNutritionIssueIds){
    List<Recipe> recipes = recipeRepo.findAllRecipesByTypeOfMealAndStatus(recipeType, calorieStatus, userNutritionIssueIds);
    return recipes.stream().map(t -> RecipeService.toPayload(t)).collect(Collectors.toList());
  }

  public RecipeDto chooseMeal(List<RecipeDto> recipeList, UserDto user){
    RecipeDto chosenMeal = recipeList.get(0);

    Integer counter = chosenMeal.getRecipeCount().getCount();
    if (counter == null) return null;
    for (RecipeDto recipe : recipeList) {
      if (counter > recipe.getRecipeCount().getCount()){
        counter = recipe.getRecipeCount().getCount();
        chosenMeal = recipe;
      }
    }
    return chosenMeal;
  }
}
