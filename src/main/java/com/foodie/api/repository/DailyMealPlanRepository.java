package com.foodie.api.repository;

import com.foodie.api.model.entities.DailyMealPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyMealPlanRepository extends JpaRepository<DailyMealPlan, Long> {
    @Query(value = "SELECT p.id, p.date_id, p.rating, p.breakfast_id, p.lunch_id, p.dinner_id FROM daily_meal_plans p WHERE p.user_id = ?1", nativeQuery = true)
    List<DailyMealPlan> findDailyMealPlanofUser(Long id);
}
