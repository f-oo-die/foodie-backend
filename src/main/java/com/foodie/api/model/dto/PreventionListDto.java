package com.foodie.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreventionListDto {
  private Long id;
  private RecipeDto recipe;
  private NutritionIssueDto nutritionIssue;
}
