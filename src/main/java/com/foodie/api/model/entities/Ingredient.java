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
@Table(
        name = "ingredients"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient extends EntityWithLongId {

    @Column(name = "ingredient_name", nullable = false, unique = true)
    private String ingredientName;

    @Column(name = "caloric_value", nullable = false)
    private Double caloricValue;

    @Column(name = "nutritional_value", nullable = false)
    private Double nutritionalValue;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<IngredientList> ingredientList;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<ShoppingList> shoppingLists;

}
