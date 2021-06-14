package com.foodie.api.repository;

import java.util.List;
import java.util.Optional;

import com.foodie.api.model.entities.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT distinct r.id, r.title, r.preparation, r.prep_time, r.num_of_calories, r.type_of_meal, r.calorie_status, r.count, r.thumbnail_image_url, r.main_image_url FROM recipes r, recipes_nutrition_issues n WHERE r.type_of_meal = ?1 AND r.calorie_status = ?2 AND n.nutrition_issues_id IN (?3) ORDER BY r.count LIMIT 1", nativeQuery = true)
    Optional<Recipe> findFilteredRecipe(Integer typeOfMeal, Integer status, List<Long> nutritionIssueIds);

    @Query(value = "SELECT distinct r.id, r.title, r.preparation, r.num_of_calories, r.prep_time, r.type_of_meal, r.calorie_status, r.count, r.thumbnail_image_url, r.main_image_url, ru.users_id, ru.favorite_recipes_id FROM recipes r, users_favorite_recipes ru WHERE ru.users_id = ?1 AND ru.favorite_recipes_id = r.id", nativeQuery = true)
    List<Recipe> findFavoriteRecipesOfUser(Long userId);

    @Modifying
    @Query(value = "INSERT INTO users_favorite_recipes(users_id, favorite_recipes_id) VALUES(?1, ?2)", nativeQuery = true)
    void saveRelationship(Long userId, Long recipeId);

    @Modifying
    @Query(value = "DELETE FROM users_favorite_recipes WHERE users_id = ?1 AND favorite_recipes_id = ?2", nativeQuery = true)
    void deleteRelationship(Long userId, Long recipeId);

    List<Recipe> findTop6ByOrderByCountDesc();
}
