package com.foodie.api.repository;


import java.util.List;
import java.util.Optional;

import com.foodie.api.model.entities.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    @Query(value = "SELECT s.id, s.title, s.user_id FROM shopping_lists s WHERE s.user_id = ?1", nativeQuery = true)
    List<ShoppingList> findShoppingListsOfUser(Long userId);

    @Query(value = "SELECT s.id, s.title, s.user_id FROM shopping_lists s WHERE s.user_id = ?1 AND s.id = ?2", nativeQuery = true)
    Optional<ShoppingList> findByIdAndUserId(Long userId, Long id);

    @Modifying
    @Query(value = "DELETE FROM shopping_lists s WHERE s.user_id = ?1 AND s.id = ?2", nativeQuery = true)
    void deleteByUserIdAndId(Long userId, Long id);

    @Modifying
    @Query(value = "DELETE FROM shopping_lists_ingredients s WHERE s.shopping_lists_id = ?1 AND s.ingredients_id = ?2", nativeQuery = true)
    void deleteRelationship(Long id, Long ingredientId);
}



