package com.foodie.api.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ShoppingList> shoppingLists;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<NutritionIssueList> nutritionIssueLists;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<FavoriteRecipe> favoriteRecipes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<DailyMealPlan> dailyMealPlans;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Counter> recipeCount;
}
