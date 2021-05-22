package com.foodie.api.model.dto;



import com.foodie.api.model.entities.User;
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
    private String dateId;
    private RecipeDto breakfast;
    private RecipeDto lunch;
    private RecipeDto dinner;
    private User userId;
    private Integer rating;
}
