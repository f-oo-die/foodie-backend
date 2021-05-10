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
public class IngredientDto{
    private Long id;
    private String ingredientName;
    private Double caloricValue;
    private Double nutritionalValue;
    private Set<IngredientListDto> ingredientList;
}
