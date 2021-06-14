package com.foodie.api.service;

import java.util.Set;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.IngredientListDto;
import com.foodie.api.model.entities.IngredientList;
import com.foodie.api.repository.IngredientListRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientListService {

    private final IngredientListRepository ingredientListRepo;

    public Set<IngredientListDto> getIngredientListForRecipe(Long recipeId) {
        Set<IngredientList> ingredientList = ingredientListRepo.findIngredientsOfRecipe(recipeId);
        return ingredientList.stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toSet());
    }

    public IngredientListDto save(IngredientListDto payload) {
        IngredientList ingredientList = fromPayload(payload);
        ingredientList = ingredientListRepo.save(ingredientList);
        return toPayload(ingredientList);
    }

    public static IngredientList fromPayload(IngredientListDto payload) {
        IngredientList ingredientList = new IngredientList();
        if (payload.getId() != null) ingredientList.setId(payload.getId());
        ingredientList.setAmount(payload.getAmount());
        ingredientList.setAmountLabel(payload.getAmountLabel());
        ingredientList.setIngredient(IngredientService.fromPayload(payload.getIngredient()));
        return ingredientList;
    }

    public static IngredientListDto toPayload(IngredientList ingredientList) {
        IngredientListDto payload = new IngredientListDto();
        payload.setId(ingredientList.getId());
        payload.setAmount(ingredientList.getAmount());
        payload.setAmountLabel(ingredientList.getAmountLabel());
        payload.setIngredient(IngredientService.toPayload(ingredientList.getIngredient()));
        return payload;
    }
}
