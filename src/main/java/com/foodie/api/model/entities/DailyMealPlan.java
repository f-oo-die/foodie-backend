package com.foodie.api.model.entities;

import javax.persistence.Column;
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
    name = "daily_meal_plans"
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyMealPlan extends EntityWithLongId {

  @Column(name = "date_id", nullable = false)
  private String dateId;

  @Column(name = "rating", nullable = true)
  private Integer rating;

  @ManyToOne
  @JoinColumn(name = "breakfast_id", referencedColumnName = "id")
  private Recipe breakfast;

  @ManyToOne
  @JoinColumn(name = "lunch_id", referencedColumnName = "id")
  private Recipe lunch;

  @ManyToOne
  @JoinColumn(name = "dinner_id", referencedColumnName = "id")
  private Recipe dinner;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
