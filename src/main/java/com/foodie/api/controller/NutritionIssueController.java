package com.foodie.api.controller;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.model.dto.NutritionIssueDto;
import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.model.entities.NutritionIssue;
import com.foodie.api.service.NutritionIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutrition")
@RequiredArgsConstructor
public class NutritionIssueController {

    private final NutritionIssueService service;

    @GetMapping
    public ResponseEntity<Collection<NutritionIssueDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutritionIssueDto> get(@PathVariable Long id){
        NutritionIssueDto result = service.getNutritionIssue(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<NutritionIssueDto> save(
            @RequestBody NutritionIssueDto nutritionIssue){
        return ResponseEntity.status(HttpStatus.OK).body(service.save(nutritionIssue));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutritionIssueDto> update(
            @PathVariable Long id,
            @RequestBody NutritionIssueDto nutritionIssue
    ){
        NutritionIssueDto result = service.update(id, nutritionIssue);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
