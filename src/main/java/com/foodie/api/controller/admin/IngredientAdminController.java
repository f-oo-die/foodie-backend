package com.foodie.api.controller.admin;

import java.util.Collection;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.service.IngredientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/ingredients")
@RequiredArgsConstructor
public class IngredientAdminController {

    private final IngredientService service;

    @GetMapping
    public ResponseEntity<Collection<IngredientDto>> getAll() {
        Collection<IngredientDto> allIngredients = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(allIngredients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> get(
            @PathVariable Long id
    ) {
        IngredientDto result = service.getIngredient(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<IngredientDto> save(
            @RequestBody IngredientDto ingredient
    ) {
        IngredientDto result = service.save(ingredient);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> update(
            @PathVariable Long id,
            @RequestBody IngredientDto ingredient
    ) {
        IngredientDto result = service.update(id, ingredient);
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
