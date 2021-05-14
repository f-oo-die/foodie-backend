package com.foodie.api.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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
