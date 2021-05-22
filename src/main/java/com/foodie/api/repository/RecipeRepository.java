package com.foodie.api.repository;

import java.util.List;

import com.foodie.api.model.entities.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  
  @Query(value = "SELECT r.id, r.title, r.preparation, r.num_of_calories, r.type_of_meal FROM recipes r WHERE r.type_of_meal = ?1 AND r.calorie_status = ?2", nativeQuery = true)
  List<Recipe> findAllRecipesByTypeOfMealAndStatus(Integer typeOfMeal, Integer status);
}
