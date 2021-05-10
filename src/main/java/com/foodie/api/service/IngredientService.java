package com.foodie.api.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.model.entities.Ingredient;
import com.foodie.api.repository.IngredientRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {

  private final IngredientRepository ingredientRepo;

  public Collection<IngredientDto> getAll(){
    return ingredientRepo.findAll().stream()
    .map(this::toPayload)
    .collect(Collectors.toList());
  }

  public IngredientDto getIngredient(Long id){
    Optional<Ingredient> ingredient = ingredientRepo.findById(id);
    if (ingredient.isPresent()){
      return toPayload(ingredient.get());
    }
    throw new RuntimeException("Ingredient with id " + id + " does not exist!");
  }

  public IngredientDto save(IngredientDto payload){
    Ingredient ingredient = fromPayload(payload);
    ingredient = ingredientRepo.save(ingredient);
    return toPayload(ingredient);
  }

  public IngredientDto update(Long id, IngredientDto payload){
    getIngredient(id);

    Ingredient ingredient = fromPayload(payload);
    ingredient.setId(id);
    ingredient = ingredientRepo.save(ingredient);
    return toPayload(ingredient);
  }

  private Ingredient fromPayload(IngredientDto payload) {
    Ingredient ingredient = new Ingredient();
    ingredient.setIngredientName(payload.getIngredientName());
    ingredient.setCaloricValue(payload.getCaloricValue());
    ingredient.setNutritionalValue(payload.getNutritionalValue());
    return ingredient;
  }
  private IngredientDto toPayload(Ingredient ingredient) {
    IngredientDto payload = new IngredientDto();
    payload.setId(ingredient.getId());
    payload.setIngredientName(ingredient.getIngredientName());
    payload.setCaloricValue(ingredient.getCaloricValue());
    payload.setNutritionalValue(ingredient.getNutritionalValue());
    return payload;
  }
}