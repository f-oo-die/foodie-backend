package com.foodie.api.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "nutrition_issue_lists")
@Getter
@Setter
@NoArgsConstructor
public class NutritionIssueList extends EntityWithLongId{

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "nutrition_issue_id", referencedColumnName = "id")
    private NutritionIssue nutritionIssue;
}
