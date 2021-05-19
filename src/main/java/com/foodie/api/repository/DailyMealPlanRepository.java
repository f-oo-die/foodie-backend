package com.foodie.api.repository;

import com.foodie.api.model.entities.DailyMealPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMealPlanRepository extends JpaRepository<DailyMealPlan, Long> {
  
}
