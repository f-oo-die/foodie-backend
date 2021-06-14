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
public class NutritionIssueDto {
    private Long id;
    private String name;
    private Set<UserDto> users;
    private Set<RecipeDto> recipes;
}
