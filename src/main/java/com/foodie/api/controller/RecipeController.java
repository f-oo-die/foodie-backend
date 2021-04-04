package com.foodie.api.controller;

import java.util.List;

import com.foodie.api.model.Recipe;
import com.foodie.api.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

  @Autowired
  RecipeService service;

  @GetMapping
  public ResponseEntity<List<Recipe>> list(){
    List<Recipe> allRecipes = service.getList();

    return ResponseEntity.status(HttpStatus.OK).body(allRecipes);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Recipe> get(
    @PathVariable Long id
  ){
    Recipe recipe = service.getRecipe(id);

    return ResponseEntity.status(HttpStatus.OK).body(recipe);
  }

  @PostMapping
  public ResponseEntity<Recipe> save(
    @RequestBody Recipe recipe
  ){
    Recipe savedRecipe = service.save(recipe);

    return ResponseEntity.status(HttpStatus.OK).body(savedRecipe);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Recipe> update(
    @PathVariable Long id,
    @RequestBody Recipe recipe
  ){
    Recipe updatedRecipe = service.update(id, recipe);

    return ResponseEntity.status(HttpStatus.OK).body(updatedRecipe);
  }
}
