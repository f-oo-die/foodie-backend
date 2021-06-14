package com.foodie.api.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.model.entities.IngredientList;
import com.foodie.api.model.entities.Recipe;
import com.foodie.api.repository.IngredientListRepository;
import com.foodie.api.repository.RecipeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
@javax.transaction.Transactional
public class RecipeService {

    private final RecipeRepository recipeRepo;
    private final IngredientListRepository ingredientListRepository;

    public Collection<RecipeDto> getAll() {
        return recipeRepo.findAll().stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toList());
    }

    public RecipeDto getRecipe(Long id) {
        Optional<Recipe> recipe = recipeRepo.findById(id);
        if (recipe.isPresent()) {
            return toPayload(recipe.get());
        }
        throw new RuntimeException("Recipe with id " + id + " does not exist!");
    }

    public Collection<RecipeDto> getLimited() {
        return recipeRepo.findTop6ByOrderByCountDesc().stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toList());
    }

    public RecipeDto save(RecipeDto payload) {
        Recipe recipe = fromPayload(payload);
        recipe.setCount(0);
        recipe = recipeRepo.save(recipe);
        for (IngredientList ingredientList : recipe.getIngredientList()) {
            ingredientList.setRecipe(recipe);
            ingredientListRepository.save(ingredientList);
        }
        return toPayload(recipe);
    }

    public RecipeDto update(Long id, RecipeDto payload) {
        getRecipe(id);

        Recipe recipe = fromPayload(payload);
        recipe.setId(id);
        recipe = recipeRepo.save(recipe);
        for (IngredientList ingredientList : recipe.getIngredientList()) {
            ingredientList.setRecipe(recipe);
            ingredientListRepository.save(ingredientList);
        }
        return toPayload(recipe);
    }

    public void delete(Long id) {
        recipeRepo.deleteById(id);
    }

    public Recipe updateCount(RecipeDto payload) {
        Recipe recipe = fromPayload(payload);
        recipe.setCount(recipe.getCount() + 1);
        recipe = recipeRepo.save(recipe);
        for (IngredientList ingredientList : recipe.getIngredientList()) {
            ingredientList.setRecipe(recipe);
            ingredientListRepository.save(ingredientList);
        }
        return recipe;
    }

    public static Recipe fromPayload(RecipeDto payload) {
        Recipe recipe = new Recipe();
        if (payload.getId() != null) recipe.setId(payload.getId());
        recipe.setTitle(payload.getTitle());
        recipe.setPreparation(payload.getPreparation());
        recipe.setNumOfCalories(payload.getNumOfCalories());
        recipe.setTypeOfMeal(payload.getTypeOfMeal());
        recipe.setCalorieStatus(payload.getCalorieStatus());
        recipe.setPreparationTime(payload.getPreparationTime());
        recipe.setCount(payload.getCount());
        recipe.setThumbnailImageUrl(payload.getThumbnailImageUrl());
        recipe.setMainImageUrl(payload.getMainImageUrl());
        recipe.setIngredientList(payload.getIngredientList().stream()
                .map(t -> IngredientListService.fromPayload(t))
                .collect(Collectors.toSet()));
        recipe.setNutritionIssues(payload.getNutritionIssues().stream()
                .map(t -> NutritionIssueService.fromPayload(t))
                .collect(Collectors.toSet()));
        return recipe;
    }

    public static RecipeDto toPayload(Recipe recipe) {
        RecipeDto payload = new RecipeDto();
        payload.setId(recipe.getId());
        payload.setTitle(recipe.getTitle());
        payload.setPreparation(recipe.getPreparation());
        payload.setNumOfCalories(recipe.getNumOfCalories());
        payload.setTypeOfMeal(recipe.getTypeOfMeal());
        payload.setCalorieStatus(recipe.getCalorieStatus());
        payload.setPreparationTime(recipe.getPreparationTime());
        payload.setCount(recipe.getCount());
        payload.setThumbnailImageUrl(recipe.getThumbnailImageUrl());
        payload.setMainImageUrl(recipe.getMainImageUrl());
        payload.setIngredientList(recipe.getIngredientList().stream()
                .map(t -> IngredientListService.toPayload(t))
                .collect(Collectors.toSet()));
        payload.setNutritionIssues(recipe.getNutritionIssues().stream()
                .map(t -> NutritionIssueService.toPayload(t))
                .collect(Collectors.toSet()));
        return payload;
    }
}
