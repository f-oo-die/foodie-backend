package com.foodie.api.repository;


import com.foodie.api.model.entities.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

}



