package com.foodie.api.repository;

import com.foodie.api.model.entities.NutritionIssue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionIssueRepository extends JpaRepository<NutritionIssue, Long> {

}
