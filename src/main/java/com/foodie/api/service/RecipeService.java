package com.foodie.api.service;

import java.util.ArrayList;
import java.util.List;

import com.foodie.api.model.Recipe;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {
  private ArrayList<Recipe> recipes = new ArrayList<>();

  public RecipeService(){
    recipes.add(new Recipe(0L, "Chicken salad", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 200, 0));
    recipes.add(new Recipe(1L, "Tuna salad", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 100, 1));
    recipes.add(new Recipe(2L, "Spicy salad", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 300, 2));
  }

  public List<Recipe> getList(){
    return this.recipes;
  }

  public Recipe getRecipe(Long id){
    if (id == null) return null;
    
    for (Recipe recipe : recipes) 
      if (id.equals(recipe.getId())) return recipe;

    return null;
  }

  public Recipe save(Recipe recipe){
    recipes.add(recipe);
    return recipe;
  }

  public Recipe update(Long id, Recipe recipe){
    if (id == null) return null;

    for (Recipe existingRecipe : recipes) {
      if (id.equals(existingRecipe.getId())) {
        existingRecipe.setTitle(recipe.getTitle());
        existingRecipe.setPreparation(recipe.getPreparation());
        existingRecipe.setNumOfCalories(recipe.getNumOfCalories());
        existingRecipe.setTypeOfMeal(recipe.getTypeOfMeal());
        return existingRecipe;
      }
    }
    return null;
  }
}