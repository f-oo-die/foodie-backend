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
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer weight;
    private Integer height;
    private String profileImageUrl;
    private Set<ShoppingListDto> shoppingLists;
    private Set<NutritionIssueDto> nutritionIssues;
    private Set<RecipeDto> favoriteRecipes;
    private Set<DailyMealPlanDto> dailyMealPlans;

    private Double getBMI() {
        if (weight == null || height == null) return 0.0;
        return Double.valueOf(weight) / (Math.pow(height / 10, 2)) * 100;
    }

    public Integer getRecommendedCalorieStatus() {
        double BMI = getBMI();
        if (BMI > 25) return 2;
        else if (BMI > 18.5 && BMI < 24.9) return 1;
        return 0;
    }
}
