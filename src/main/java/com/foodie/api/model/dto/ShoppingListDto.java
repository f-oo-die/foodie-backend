package com.foodie.api.model.dto;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDto {
    private Long id;
    private UserDto user;
    private Set<IngredientDto> ingredients;
}
