package com.foodie.api.controller;

import java.util.Collection;

import com.foodie.api.model.dto.ShoppingListDto;
import com.foodie.api.service.ShoppingListService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping-list")
@RequiredArgsConstructor
public class ShoppingListController {

    private final ShoppingListService service;

    @GetMapping("{userId}")
    public ResponseEntity<Collection<ShoppingListDto>> getAll(
            @PathVariable Long userId
    ) {
        Collection<ShoppingListDto> allShoppingLists = service.getAll(userId);

        return ResponseEntity.status(HttpStatus.OK).body(allShoppingLists);
    }

    @PostMapping("{userId}")
    public ResponseEntity<ShoppingListDto> save(
            @PathVariable Long userId,
            @RequestBody ShoppingListDto shoppingList
    ) {
        ShoppingListDto result = service.save(userId, shoppingList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<ShoppingListDto> get(
            @PathVariable Long userId,
            @PathVariable Long id
    ) {
        ShoppingListDto result = service.getShoppingList(userId, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity<ShoppingListDto> update(
            @PathVariable Long userId,
            @PathVariable Long id,
            @RequestBody ShoppingListDto shoppingList
    ) {
        ShoppingListDto result = service.update(userId, id, shoppingList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable Long userId,
            @PathVariable Long id
    ) {
        service.delete(userId, id);
        return ResponseEntity.noContent().build();
    }
}

