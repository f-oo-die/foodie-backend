package com.foodie.api.repository;

import java.util.Set;

import com.foodie.api.model.entities.IngredientList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientListRepository extends JpaRepository<IngredientList, Long> {

    @Query(value = "SELECT i.id, i.ingredient_id, i.amount, i.amount_label, i.recipe_id FROM ingredient_lists i WHERE i.recipe_id = ?1", nativeQuery = true)
    Set<IngredientList> findIngredientsOfRecipe(Long id);

}
