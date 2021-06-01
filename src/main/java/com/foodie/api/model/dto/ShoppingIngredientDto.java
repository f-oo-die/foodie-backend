package com.foodie.api.model.dto;

import com.foodie.api.model.entities.Ingredient;
import com.foodie.api.model.entities.ShoppingList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ShoppingIngredientDto {
    private Long id;
    private IngredientDto ingredient;
    private ShoppingListDto shoppingList;


}
