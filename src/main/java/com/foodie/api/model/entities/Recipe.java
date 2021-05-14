package com.foodie.api.model.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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
public class Recipe extends EntityWithLongId{

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "preparation", nullable = false)
  private String preparation;

  @Column(name = "num_of_calories", nullable = false)
  private Integer numOfCalories;

  @Column(name = "type_of_meal", nullable = false)
  private Integer typeOfMeal;

  @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
  private List<IngredientList> ingredientList;

  @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
  private Set<FavoriteRecipe> favoriteRecipes;

  @OneToMany(mappedBy = "breakfast", fetch = FetchType.LAZY)
  private Set<DailyMealPlan> breakfastSet;

  @OneToMany(mappedBy = "lunch", fetch = FetchType.LAZY)
  private Set<DailyMealPlan> lunchSet;

  @OneToMany(mappedBy = "dinner", fetch = FetchType.LAZY)
  private Set<DailyMealPlan> dinnerSet;

  @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
  private Set<Counter> recipeCount;

  @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
  private Set<PreventionList> preventionList;
}
