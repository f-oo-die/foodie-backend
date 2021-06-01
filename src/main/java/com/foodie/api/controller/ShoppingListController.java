package com.foodie.api.controller;
import java.util.Collection;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.model.dto.NutritionIssueDto;
import com.foodie.api.model.dto.ShoppingListDto;
import com.foodie.api.service.ShoppingListService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping-list")
@RequiredArgsConstructor

public class ShoppingListController  {
    private final ShoppingListService service;
    @GetMapping
    public ResponseEntity<Collection<ShoppingListDto>> getAll(){
        Collection<ShoppingListDto> allShoppingList = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(allShoppingList);
    }
    @PostMapping
    public ResponseEntity<ShoppingListDto> save(
            @RequestBody ShoppingListDto shoppingIngredient){
        return ResponseEntity.status(HttpStatus.OK).body(service.save(shoppingIngredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListDto> get(
            @PathVariable Long id
    ){
        ShoppingListDto result1 = service.getShoppingList(id);
        return ResponseEntity.status(HttpStatus.OK).body(result1);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListDto> update(
            @PathVariable Long id,
            @RequestBody ShoppingListDto shoppingList
    ){
        ShoppingListDto result = service.update(id, shoppingList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

