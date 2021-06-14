package com.foodie.api.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.model.entities.Recipe;
import com.foodie.api.repository.RecipeRepository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public RecipeDto save(Long userId, Long recipeId) {
        Optional<Recipe> favoriteRecipe = recipeRepository.findById(recipeId);
        if (favoriteRecipe.isPresent()) {
            recipeRepository.saveRelationship(userId, recipeId);
            return RecipeService.toPayload(favoriteRecipe.get());
        }
        throw new RuntimeException("Recipe with id " + recipeId + " failed to add to favorite!");
    }

    public RecipeDto getFavoriteRecipe(Long userId, Long recipeId) {
        Optional<Recipe> favoriteRecipe = recipeRepository.findById(recipeId);
        if (favoriteRecipe.isPresent()) {
            return RecipeService.toPayload(favoriteRecipe.get());
        }
        throw new RuntimeException("Recipe with id " + recipeId + " is not added to favorite!");
    }

    @Transactional
    public void delete(Long userId, Long recipeId) {
        recipeRepository.deleteRelationship(userId, recipeId);
    }
}
