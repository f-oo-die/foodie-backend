package com.foodie.api.service;

import com.foodie.api.model.dto.NutritionIssueDto;
import com.foodie.api.model.entities.NutritionIssue;
import com.foodie.api.repository.NutritionIssueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class NutritionIssueServiceTest {
    NutritionIssueService nutritionIssueService;
    NutritionIssueRepository nutritionIssueRepository;

    @BeforeEach
    void setUp() {
        this.nutritionIssueRepository = Mockito.mock(NutritionIssueRepository.class);
        this.nutritionIssueService = new NutritionIssueService(nutritionIssueRepository);

    }

    @Test
    void getNutritionIssue() {
        when(nutritionIssueRepository.findById(266L)).thenReturn(Optional.of(new NutritionIssue()));
        NutritionIssueDto nutritionIssueDto = nutritionIssueService.getNutritionIssue(266L);

        assertNotNull(nutritionIssueDto);
    }
}
