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

  @Column(name = "IngredientName", nullable = false, unique = true)
  private String IngredientName;
  @Column(name = "CaloricValue", nullable = false)
  private Double CaloricValue;
  @Column(name = "NutritionalValue", nullable = false)
  private Double NutritionalValue;
  @Column(name = "AllNatural", nullable = false)
  private Boolean AllNatural;
  @Column(name = "Additives")
  private String Additives;

  
}
