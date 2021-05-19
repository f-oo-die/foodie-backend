package com.foodie.api.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "prevention_lists"
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreventionList extends EntityWithLongId{

  @ManyToOne
  @JoinColumn(name = "recipe_id", referencedColumnName = "id")
  private Recipe recipe;

  @ManyToOne
  @JoinColumn(name = "nutrition_issue_id", referencedColumnName = "id")
  private NutritionIssue nutritionIssue;
}
