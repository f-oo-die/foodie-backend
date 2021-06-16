package com.foodie.api.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends EntityWithLongId {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "preparation", nullable = false, columnDefinition = "text")
    private String preparation;

    @Column(name = "num_of_calories", nullable = false)
    private Integer numOfCalories;

    @Column(name = "type_of_meal", nullable = false)
    private Integer typeOfMeal;

    @Column(name = "calorie_status", nullable = false)
    private Integer calorieStatus;

    @Column(name = "prep_time", nullable = false)
    private Integer preparationTime;

    @Column(name = "thumbnail_image_url", nullable = false)
    private String thumbnailImageUrl;

    @Column(name = "main_image_url", nullable = false)
    private String mainImageUrl;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<IngredientList> ingredientList;

    @Column(name = "count", nullable = false)
    private Integer count;

    @ManyToMany(mappedBy = "favoriteRecipes", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<User> users;

    @OneToMany(mappedBy = "breakfast", fetch = FetchType.LAZY)
    private Set<DailyMealPlan> breakfastSet;

    @OneToMany(mappedBy = "lunch", fetch = FetchType.LAZY)
    private Set<DailyMealPlan> lunchSet;

    @OneToMany(mappedBy = "dinner", fetch = FetchType.LAZY)
    private Set<DailyMealPlan> dinnerSet;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<NutritionIssue> nutritionIssues;
}
