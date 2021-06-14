package com.foodie.api.controller;

import java.util.Collection;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.service.IngredientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

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
}
