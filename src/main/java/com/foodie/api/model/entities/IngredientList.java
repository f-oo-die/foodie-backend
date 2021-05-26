package com.foodie.api.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredient_lists")
@Getter
@Setter
@NoArgsConstructor
public class IngredientList extends EntityWithLongId {

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "amount_label", nullable = false)
    private String amountLabel;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;
}
