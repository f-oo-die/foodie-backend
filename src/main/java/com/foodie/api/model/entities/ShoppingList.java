package com.foodie.api.model.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shopping_lists")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingList extends EntityWithLongId{

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "shoppingList", fetch = FetchType.LAZY)
    private Set<ShoppingIngredient> shoppingIngredients;
}
