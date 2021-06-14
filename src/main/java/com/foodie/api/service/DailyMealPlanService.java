package com.foodie.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.DailyMealPlanDto;
import com.foodie.api.model.dto.UserDto;
import com.foodie.api.model.entities.DailyMealPlan;
import com.foodie.api.repository.DailyMealPlanRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DailyMealPlanService {

    private final DailyMealPlanRepository dailyMealPlanRepo;
    private final CreateDailyMealPlanService createDailyMealPlanService;
    private final UserService userService;
    private final RecipeService recipeService;

    public List<DailyMealPlanDto> getDailyMealPlanList(Long userId) {
        List<DailyMealPlan> dailyMealPlan = dailyMealPlanRepo.findDailyMealPlanofUser(userId);
        if (!dailyMealPlan.isEmpty()) dailyMealPlan.remove(0);
        return dailyMealPlan.stream().map(t -> toPayload(t)).collect(Collectors.toList());
    }

    public DailyMealPlanDto getLatestDailyMealPlan(Long userId) {
        Optional<DailyMealPlan> dailyMealPlan = dailyMealPlanRepo.findLatestDailyMealPlanofUser(userId);
        if (dailyMealPlan.isPresent()) {
            return toPayload(dailyMealPlan.get());
        }
        return null;
    }

    public DailyMealPlanDto create(Long userId) {
        UserDto user = userService.getUser(userId);
        DailyMealPlanDto dailyMealPlan = createDailyMealPlanService.createDailyMealPlan(user);
        dailyMealPlan = save(dailyMealPlan);
        return dailyMealPlan;
    }

    public DailyMealPlanDto save(DailyMealPlanDto payload) {
        DailyMealPlan dailyMealPlan = fromPayload(payload);
        dailyMealPlan = dailyMealPlanRepo.save(dailyMealPlan);
        return toPayload(dailyMealPlan);
    }

    private DailyMealPlan fromPayload(DailyMealPlanDto payload) {
        DailyMealPlan dailyMealPlan = new DailyMealPlan();
        dailyMealPlan.setDate(LocalDate.now());
        dailyMealPlan.setUser(UserService.fromPayload(payload.getUser()));
        dailyMealPlan.setBreakfast(recipeService.updateCount(payload.getBreakfast()));
        dailyMealPlan.setLunch(recipeService.updateCount(payload.getLunch()));
        dailyMealPlan.setDinner(recipeService.updateCount(payload.getDinner()));
        return dailyMealPlan;
    }

    public static DailyMealPlanDto toPayload(DailyMealPlan dailyMealPlan) {
        DailyMealPlanDto payload = new DailyMealPlanDto();
        payload.setId(dailyMealPlan.getId());
        payload.setDate(dailyMealPlan.getDate());
        payload.setBreakfast(RecipeService.toPayload(dailyMealPlan.getBreakfast()));
        payload.setLunch(RecipeService.toPayload(dailyMealPlan.getLunch()));
        payload.setDinner(RecipeService.toPayload(dailyMealPlan.getDinner()));
        return payload;
    }
}
