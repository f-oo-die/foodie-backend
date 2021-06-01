package com.foodie.api.model.dto;
import com.foodie.api.model.entities.ShoppingIngredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDto {
    private Long id;
    private UserDto user;
    private Set<ShoppingIngredientDto> shoppingIngredients;




}
