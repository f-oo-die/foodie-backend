package com.foodie.api.service;

import com.foodie.api.model.dto.NutritionIssueDto;
import com.foodie.api.model.entities.NutritionIssue;
import com.foodie.api.repository.NutritionIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@javax.transaction.Transactional
@RequiredArgsConstructor
public class NutritionIssueService {

    private final NutritionIssueRepository nutritionIssueRepository;

    public Collection<NutritionIssueDto> getAll() {
        return nutritionIssueRepository.findAll().stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toList());
    }

    public NutritionIssueDto getNutritionIssue(Long id) {
        Optional<NutritionIssue> nutritionIssue = nutritionIssueRepository.findById(id);
        if (nutritionIssue.isPresent()) {
            return toPayload(nutritionIssue.get());
        }
        throw new RuntimeException("Nutrition Issue with id " + id + " does not exist!");
    }

    public NutritionIssueDto save(NutritionIssueDto payload) {
        NutritionIssue nutritionIssue = fromPayload(payload);
        nutritionIssue = nutritionIssueRepository.save(nutritionIssue);
        return toPayload(nutritionIssue);
    }

    public NutritionIssueDto update(Long id, NutritionIssueDto payload) {
        getNutritionIssue(id);

        NutritionIssue nutritionIssue = fromPayload(payload);
        nutritionIssue.setId(id);
        nutritionIssue = nutritionIssueRepository.save(nutritionIssue);
        return toPayload(nutritionIssue);
    }

    public void delete(Long id) {
        nutritionIssueRepository.deleteById(id);
    }

    public static NutritionIssue fromPayload(NutritionIssueDto payload) {
        NutritionIssue nutritionIssue = new NutritionIssue();
        if (payload.getId() != null) nutritionIssue.setId(payload.getId());
        nutritionIssue.setName(payload.getName());
        return nutritionIssue;
    }

    public static NutritionIssueDto toPayload(NutritionIssue nutritionIssue) {
        NutritionIssueDto payload = new NutritionIssueDto();
        payload.setId(nutritionIssue.getId());
        payload.setName(nutritionIssue.getName());
        return payload;
    }

}
