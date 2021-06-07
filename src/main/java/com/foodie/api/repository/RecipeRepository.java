package com.foodie.api.repository;

import java.util.List;

import com.foodie.api.model.entities.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  
  @Query(value = "SELECT distinct r.id, r.title, r.preparation, r.num_of_calories, r.type_of_meal, r.calorie_status, r.recipe_count FROM recipes r, recipes_nutrition_issues n WHERE r.type_of_meal = ?1 AND r.calorie_status = ?2 AND n.nutrition_issues_id IN (?3)", nativeQuery = true)
  List<Recipe> findAllRecipesByTypeOfMealAndStatus(Integer typeOfMeal, Integer status, List<Long> nutritionIssueIds);

  @Query(value = "SELECT r.id, r.title, r.preparation, r.num_of_calories, r.type_of_meal, r.calorie_status, r.recipe_count, ru.users_id, ru.favorite_recipes_id FROM recipes r, users_favorite_recipes ru WHERE ru.users_id = ?1", nativeQuery = true)
  List<Recipe> findFavoriteRecipesOfUser(Long userId);
}
