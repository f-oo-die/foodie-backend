package com.foodie.api.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shopping_lists")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "shoppingList", fetch = FetchType.LAZY)
    private Set<ShoppingIngredient> shoppingIngredients;
}
