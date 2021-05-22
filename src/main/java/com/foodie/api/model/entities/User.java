package com.foodie.api.model.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class User extends EntityWithLongId implements UserDetails {

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked=false;
    private Boolean enabled=true;

    public User(String firstName,
                String lastName,
                String email,
                String password,
                UserRole userRole){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.password=password;
        this.userRole=userRole;
        this.height=180;
        this.weight=90;
    }



    @Column(name = "weight", nullable = true)
    private Integer weight;

    @Column(name = "height", nullable = true)
    private Integer height;

    @Column(name = "gender", nullable = true)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {return password;}

    public String getUsername() {
        return email;
    }

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
