package com.foodie.api.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "preparation", nullable = false)
  private String preparation;

  @Column(name = "num_of_calories", nullable = false)
  private Integer numOfCalories;

  @Column(name = "type_of_meal", nullable = false)
  private Integer typeOfMeal;
}
