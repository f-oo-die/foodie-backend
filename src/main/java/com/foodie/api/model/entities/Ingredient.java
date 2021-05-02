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
@Table(
    name = "ingredients"
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "ingredientName", nullable = false, unique = true)
  private String ingredientName;
  @Column(name = "caloricValue", nullable = false)
  private Double caloricValue;
  @Column(name = "nutritionalValue", nullable = false)
  private Double nutritionalValue;
  @Column(name = "allNatural", nullable = false)
  private Boolean allNatural;
  @Column(name = "additives")
  private String additives;

  
}
