package com.foodie.api.controller.admin;

import java.util.Collection;

import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.service.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/recipes")
@RequiredArgsConstructor
public class RecipeAdminController {

    private final RecipeService service;

    @GetMapping
    public ResponseEntity<Collection<RecipeDto>> getAll() {
        Collection<RecipeDto> allRecipes = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(allRecipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> get(
            @PathVariable Long id
    ) {
        RecipeDto result = service.getRecipe(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<RecipeDto> save(
            @RequestBody RecipeDto recipe
    ) {
        RecipeDto result = service.save(recipe);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> update(
            @PathVariable Long id,
            @RequestBody RecipeDto recipe
    ) {
        RecipeDto result = service.update(id, recipe);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
