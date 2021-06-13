package com.foodie.api.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMealPlanDto {
    private long id;
    private LocalDate date;
    private RecipeDto breakfast;
    private RecipeDto lunch;
    private RecipeDto dinner;
    private UserDto user;
}
