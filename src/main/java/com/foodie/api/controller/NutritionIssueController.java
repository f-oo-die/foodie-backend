package com.foodie.api.controller;

import java.util.Collection;

import com.foodie.api.model.dto.NutritionIssueDto;
import com.foodie.api.service.NutritionIssueService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nutrition-issues")
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
