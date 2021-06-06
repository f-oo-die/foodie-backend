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
public class ShoppingListController  {

    private final ShoppingListService service;

    @GetMapping
    public ResponseEntity<Collection<ShoppingListDto>> getAll(){
        Collection<ShoppingListDto> allShoppingLists = service.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(allShoppingLists);
    }

    @PostMapping
    public ResponseEntity<ShoppingListDto> save(
        @RequestBody ShoppingListDto shoppingList){
        return ResponseEntity.status(HttpStatus.OK).body(service.save(shoppingList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListDto> get(
        @PathVariable Long id
    ){
        ShoppingListDto result = service.getShoppingList(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListDto> update(
        @PathVariable Long id,
        @RequestBody ShoppingListDto shoppingList
    ){
        ShoppingListDto result = service.update(id, shoppingList);
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

