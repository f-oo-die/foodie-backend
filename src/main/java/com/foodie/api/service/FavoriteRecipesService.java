package com.foodie.api.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.model.entities.Recipe;
import com.foodie.api.repository.RecipeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteRecipesService {

  private final RecipeRepository recipeRepository;

  public Collection<RecipeDto> getAll(Long userId) {
    List<Recipe> favoriteRecipes = recipeRepository.findFavoriteRecipesOfUser(userId);
    return favoriteRecipes.stream().map((t) -> RecipeService.toPayload(t)).collect(Collectors.toList());
  }

  public Object save(Long userId, Long recipeId) {
    return null;
  }

  public RecipeDto getFavoriteRecipe(Long userId, Long recipeId) {
    return null;
  }

  public void delete(Long userId, Long recipeId) {
  }

}
