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
    name = "counter"
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Counter extends EntityWithLongId {

  @ManyToOne
  @JoinColumn(name = "recipe_id", referencedColumnName = "id")
  private Recipe recipe;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "count", nullable = false)
  private Integer count = 0;
}
