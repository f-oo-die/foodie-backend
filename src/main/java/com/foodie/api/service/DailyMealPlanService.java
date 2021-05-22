package com.foodie.api.service;

import com.foodie.api.model.dto.DailyMealPlanDto;
import com.foodie.api.model.entities.DailyMealPlan;
import com.foodie.api.repository.DailyMealPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DailyMealPlanService {

    private final DailyMealPlanRepository dailyMealPlanRepo;
    private final CreateDailyMealPlanService createDailyMealPlanService;

    public List<DailyMealPlanDto> getDailyMealPlanList(Long userId){
        List<DailyMealPlan> dailyMealPlan= dailyMealPlanRepo.findDailyMealPlanofUser(userId);
        return dailyMealPlan.stream().map(t -> toPayload(t)).collect(Collectors.toList());
    }

    public DailyMealPlanDto getDailyMealPlan(Long id){
        Optional<DailyMealPlan> dailyMealPlan = dailyMealPlanRepo.findById(id);
        if (dailyMealPlan.isPresent()){
            return toPayload(dailyMealPlan.get());
        }
        throw new RuntimeException("DailyMealPlan with id" + id + "is not present!");
    }

    public DailyMealPlanDto create(Long userId){
        DailyMealPlanDto dailyMealPlan = new DailyMealPlanDto();
        dailyMealPlan = createDailyMealPlanService.createDailyMealPlan(userId);
        return dailyMealPlan;
    }

    // private DailyMealPlan fromPayload(DailyMealPlanDto payload){
    //     DailyMealPlan dailyMealPlan = new DailyMealPlan();
    //     dailyMealPlan.setDateId(dailyMealPlan.getDateId());
    //     dailyMealPlan.setRating(dailyMealPlan.getRating());
    //     dailyMealPlan.setBreakfast(RecipeService.fromPayload(payload.getBreakfast()));
    //     dailyMealPlan.setLunch(RecipeService.fromPayload(payload.getLunch()));
    //     dailyMealPlan.setDinner(RecipeService.fromPayload(payload.getDinner()));
    //     return dailyMealPlan;
    // }

    public static DailyMealPlanDto toPayload(DailyMealPlan dailyMealPlan){
        DailyMealPlanDto payload = new DailyMealPlanDto();
        payload.setId(dailyMealPlan.getId());
        payload.setDateId(dailyMealPlan.getDateId());
        payload.setRating(dailyMealPlan.getRating());
        payload.setBreakfast(RecipeService.toPayload(dailyMealPlan.getBreakfast()));
        payload.setLunch(RecipeService.toPayload(dailyMealPlan.getLunch()));
        payload.setDinner(RecipeService.toPayload(dailyMealPlan.getDinner()));
        return payload;
    }
}
