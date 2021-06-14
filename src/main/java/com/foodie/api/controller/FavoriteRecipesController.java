package com.foodie.api.controller;

import java.util.Collection;

import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.service.FavoriteRecipesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/favorite-recipes")
@RequiredArgsConstructor
public class FavoriteRecipesController {

    private final FavoriteRecipesService service;

    @GetMapping("{userId}")
    public ResponseEntity<Collection<RecipeDto>> getAll(
            @PathVariable Long userId
    ) {
        Collection<RecipeDto> favoriteRecipes = service.getAll(userId);

        return ResponseEntity.status(HttpStatus.OK).body(favoriteRecipes);
    }

    @PostMapping("{userId}/{recipeId}")
    public ResponseEntity<Object> save(
            @PathVariable Long userId,
            @PathVariable Long recipeId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(userId, recipeId));
    }

    @GetMapping("/{userId}/{recipeId}")
    public ResponseEntity<RecipeDto> get(
            @PathVariable Long userId,
            @PathVariable Long recipeId
    ) {
        RecipeDto result = service.getFavoriteRecipe(userId, recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{userId}/{recipeId}")
    public ResponseEntity<Object> delete(
            @PathVariable Long userId,
            @PathVariable Long recipeId
    ) {
        service.delete(userId, recipeId);
        return ResponseEntity.noContent().build();
    }
}
