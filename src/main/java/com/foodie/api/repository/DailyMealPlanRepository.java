package com.foodie.api.repository;

import com.foodie.api.model.entities.DailyMealPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DailyMealPlanRepository extends JpaRepository<DailyMealPlan, Long> {
    @Query(value = "SELECT p.id, p.date, p.user_id, p.breakfast_id, p.lunch_id, p.dinner_id FROM daily_meal_plans p WHERE p.user_id = ?1 ORDER BY id Desc", nativeQuery = true)
    List<DailyMealPlan> findDailyMealPlanofUser(Long id);

    @Query(value = "SELECT p.id, p.date, p.user_id, p.breakfast_id, p.lunch_id, p.dinner_id FROM daily_meal_plans p WHERE p.user_id = ?1 ORDER BY id Desc LIMIT 1", nativeQuery = true)
    Optional<DailyMealPlan> findLatestDailyMealPlanofUser(Long id);
}
