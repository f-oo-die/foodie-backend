package com.foodie.api.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.IngredientListDto;
import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.model.entities.Recipe;
import com.foodie.api.repository.RecipeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepo;
  private final IngredientListService ingredientListService;

  public Collection<RecipeDto> getAll(){
    return recipeRepo.findAll().stream()
    .map(t -> toPayload(t))
    .collect(Collectors.toList());
  }

  public RecipeDto getRecipe(Long id){
    Optional<Recipe> recipe = recipeRepo.findById(id);
    if (recipe.isPresent()){
      return toPayload(recipe.get());
    }
    throw new RuntimeException("Recipe with id " + id + " does not exist!");
  }

  public RecipeDto save(RecipeDto payload){
    Recipe recipe = fromPayload(payload);
    recipe = recipeRepo.save(recipe);
    return toPayload(recipe);
  }

  public RecipeDto update(Long id, RecipeDto payload){
    getRecipe(id);

    Recipe recipe = fromPayload(payload);
    recipe.setId(id);
    recipe = recipeRepo.save(recipe);
    return toPayload(recipe);
  }

  private Recipe fromPayload(RecipeDto payload) {
    Recipe recipe = new Recipe();
    recipe.setTitle(payload.getTitle());
    recipe.setPreparation(payload.getPreparation());
    recipe.setNumOfCalories(payload.getNumOfCalories());
    recipe.setTypeOfMeal(payload.getTypeOfMeal());
    return recipe;
  }

  private RecipeDto toPayload(Recipe recipe) {
    RecipeDto payload = new RecipeDto();
    payload.setId(recipe.getId());
    payload.setTitle(recipe.getTitle());
    payload.setPreparation(recipe.getPreparation());
    payload.setNumOfCalories(recipe.getNumOfCalories());
    payload.setTypeOfMeal(recipe.getTypeOfMeal());
    payload.setIngredientList(getList(recipe));
    return payload;
  }

  private List<IngredientListDto> getList(Recipe recipe){
    return ingredientListService.getIngredientListForRecipe(recipe.getId());
  }
}