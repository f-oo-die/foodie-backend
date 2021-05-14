package com.foodie.api.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "nutrition_issues")
@Getter
@Setter
@NoArgsConstructor
public class NutritionIssue extends EntityWithLongId{

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "nutritionIssue", fetch = FetchType.LAZY)
    private Set<NutritionIssueList> nutritionIssueLists;

    @OneToMany(mappedBy = "nutritionIssue", fetch = FetchType.LAZY)
    private Set<PreventionList> preventionList;
}
