package com.foodie.api.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "ingredients"
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient extends EntityWithLongId {

  @Column(name = "IngredientName", nullable = false, unique = true)
  private String IngredientName;

  @Column(name = "CaloricValue", nullable = false)
  private Double CaloricValue;

  @Column(name = "NutritionalValue", nullable = false)
  private Double NutritionalValue;

  @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
  private Set<IngredientList> ingredientList;

  @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
  private Set<ShoppingIngredient> shoppingIngredients;

}
