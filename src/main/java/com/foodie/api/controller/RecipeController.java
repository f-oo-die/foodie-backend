package com.foodie.api.controller;

import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService service;

    @GetMapping
    public ResponseEntity<Collection<RecipeDto>> getAll() {
        Collection<RecipeDto> allRecipes = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(allRecipes);
    }

    @GetMapping("homepage")
    public ResponseEntity<Collection<RecipeDto>> getLimited() {
        Collection<RecipeDto> recipes = service.getLimited();

        return ResponseEntity.status(HttpStatus.OK).body(recipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> get(
            @PathVariable Long id
    ) {
        RecipeDto result = service.getRecipe(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
