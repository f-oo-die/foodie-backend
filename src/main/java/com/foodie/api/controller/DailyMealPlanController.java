package com.foodie.api.controller;


import java.util.Collection;

import com.foodie.api.model.dto.DailyMealPlanDto;
import com.foodie.api.service.DailyMealPlanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/meal-planning")
@RequiredArgsConstructor
public class DailyMealPlanController {
    private final DailyMealPlanService service;

    @GetMapping
    public ResponseEntity<Collection<DailyMealPlanDto>> getDailyMealPlanList(
        @PathVariable Long userId
    ){
        Collection<DailyMealPlanDto> allPlans = service.getDailyMealPlanList(userId);

        return ResponseEntity.status(HttpStatus.OK).body(allPlans);
    }

    @GetMapping("{id}")
    public ResponseEntity<DailyMealPlanDto> get(
        @PathVariable Long id
    ){
        DailyMealPlanDto result = service.getDailyMealPlan(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<DailyMealPlanDto> save(
        @PathVariable Long userId
    ){
        DailyMealPlanDto result = service.create(userId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}

