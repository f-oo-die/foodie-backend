package com.foodie.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CounterDto {
  private Long id;
  private RecipeDto recipe;
  private UserDto user;
  private Integer count = 0;
}
