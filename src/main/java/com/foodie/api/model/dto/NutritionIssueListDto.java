package com.foodie.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NutritionIssueListDto {
  private Long id;
  private UserDto user;
  private NutritionIssueDto nutritionIssue;
}
