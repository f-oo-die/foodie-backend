package com.foodie.api.service;

import com.foodie.api.model.dto.*;
import com.foodie.api.model.entities.*;

import java.util.Collection;
import java.util.stream.Collectors;

import com.foodie.api.model.entities.ShoppingIngredient;
import com.foodie.api.repository.ShoppingIngredientRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ShoppingIngredientService {
    public static ShoppingIngredientDto toPayload(ShoppingIngredient shoppingIngredient) {
        ShoppingIngredientDto payload = new ShoppingIngredientDto();
        payload.setId(shoppingIngredient.getId());
        payload.setIngredient(IngredientService.toPayload(shoppingIngredient.getIngredient()));
        return payload;
    }

    public static ShoppingIngredient fromPayload(ShoppingIngredientDto payload) {
        ShoppingIngredient shoppingList = new ShoppingIngredient();
        shoppingList.setId(payload.getId());
        shoppingList.setIngredient(IngredientService.fromPayload(payload.getIngredient()));
        return shoppingList;
    }






}
