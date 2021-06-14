package com.foodie.api.model.entities;

import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends EntityWithLongId {

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "weight", nullable = true)
    private Integer weight;

    @Column(name = "height", nullable = true)
    private Integer height;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "userRole")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ShoppingList> shoppingLists;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<NutritionIssue> nutritionIssues;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Recipe> favoriteRecipes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<DailyMealPlan> dailyMealPlans;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ShoppingList> recipeCount;
}
