package com.foodie.api.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
