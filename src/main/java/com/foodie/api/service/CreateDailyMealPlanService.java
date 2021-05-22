package com.foodie.api.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.DailyMealPlanDto;
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
  private final UserService userService;
  
  public DailyMealPlanDto createDailyMealPlan(Long userId){
    DailyMealPlanDto plan = new DailyMealPlanDto();

    UserDto user = userService.getUser(userId);

    double recommendedCaloriesPerMeal = getRecommendedCaloriesPerMeal(user);
    Integer calorieStatus = getRecommendedCalorieStatus(recommendedCaloriesPerMeal);
    List<RecipeDto> breakfastList = getRecipesByTypeAndStatus(0, calorieStatus);
    List<RecipeDto> lunchList = getRecipesByTypeAndStatus(1, calorieStatus);
    List<RecipeDto> dinnerList = getRecipesByTypeAndStatus(2, calorieStatus);

    RecipeDto breakfast = chooseMeal(breakfastList, user);
    RecipeDto lunch = chooseMeal(lunchList, user);
    RecipeDto dinner = chooseMeal(dinnerList, user);

    plan = setDailyMealPlan(plan, breakfast, lunch, dinner);

    return plan;
  }

  private DailyMealPlanDto setDailyMealPlan(DailyMealPlanDto plan, RecipeDto breakfast, RecipeDto lunch,
      RecipeDto dinner) {
    plan.setBreakfast(breakfast);
    plan.setLunch(lunch);
    plan.setDinner(dinner);
    plan.setDateId(Instant.now());
    return plan;
  }

  private Integer getRecommendedCalorieStatus(double recommendedCaloriesPerMeal) {
    if (recommendedCaloriesPerMeal < 300) return 0;
    else if (recommendedCaloriesPerMeal > 300 && recommendedCaloriesPerMeal < 700) return 1;
    return 2;
  }

  private double getRecommendedCaloriesPerMeal(UserDto user) {
    return (user.getWeight() / user.getHeight()) / 3;
  }

  public List<RecipeDto> getRecipesByTypeAndStatus(Integer recipeType, Integer calorieStatus){
    List<Recipe> recipes = recipeRepo.findAllRecipesByTypeOfMealAndStatus(recipeType, calorieStatus);
    return recipes.stream().map(t -> RecipeService.toPayload(t)).collect(Collectors.toList());
  }

  public RecipeDto chooseMeal(List<RecipeDto> recipeList, UserDto user){
    RecipeDto chosenMeal = recipeList.get(0);

    int counter = chosenMeal.getRecipeCount().getCount();
    for (RecipeDto recipe : recipeList) {
      if (counter > recipe.getRecipeCount().getCount()){
        counter = recipe.getRecipeCount().getCount();
        chosenMeal = recipe;
      }
    }
    return chosenMeal;
  }
}
