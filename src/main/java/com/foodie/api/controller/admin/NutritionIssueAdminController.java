package com.foodie.api.controller.admin;

import java.util.Collection;

import com.foodie.api.model.dto.NutritionIssueDto;
import com.foodie.api.service.NutritionIssueService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/nutrition-issues")
@RequiredArgsConstructor
public class NutritionIssueAdminController {

    private final NutritionIssueService service;

    @GetMapping
    public ResponseEntity<Collection<NutritionIssueDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutritionIssueDto> get(@PathVariable Long id) {
        NutritionIssueDto result = service.getNutritionIssue(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<NutritionIssueDto> save(
            @RequestBody NutritionIssueDto nutritionIssue) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(nutritionIssue));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutritionIssueDto> update(
            @PathVariable Long id,
            @RequestBody NutritionIssueDto nutritionIssue
    ) {
        NutritionIssueDto result = service.update(id, nutritionIssue);
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
