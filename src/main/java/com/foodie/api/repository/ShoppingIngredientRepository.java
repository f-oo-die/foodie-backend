package com.foodie.api.repository;

import com.foodie.api.model.entities.ShoppingIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ShoppingIngredientRepository extends JpaRepository<ShoppingIngredient, Long> {

}
