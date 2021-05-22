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

    //Get all daily meal plans
    //get single daily meal plan

    public List<DailyMealPlanDto> getDailyMealPlanList(Long userId){
        List<DailyMealPlan> dailyMealPlan= dailyMealPlanRepo.findDailyMealPlanofUser(userId);
        return dailyMealPlan.stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toList());
    }

    public DailyMealPlanDto getDailyMealPlan(Long id){
        Optional<DailyMealPlan> dailyMealPlan = dailyMealPlanRepo.findById(id);
        if (dailyMealPlan.isPresent()){
            return toPayload(dailyMealPlan.get());
        }
        throw new RuntimeException("DailyMealPlan with id" + id + "is not present!");
    }

    /* Create daily meal plan, but requires other service
    private DailyMealPlan fromPayload(DailyMealPlanDto payload){
        DailyMealPlan dailyMealPlan = new DailyMealPlan();
        dailyMealPlan.setId(dailyMealPlan.getId());
        dailyMealPlan.setDateId(dailyMealPlan.getDateId());
        dailyMealPlan.setRating(dailyMealPlan.getRating());
        return dailyMealPlan;
    }
    */

    //Needs bugfixing, but general logic is here
    public static DailyMealPlanDto toPayload(DailyMealPlan dailyMealPlan){
        DailyMealPlanDto payload = new DailyMealPlanDto();
        payload.setId(dailyMealPlan.getId());
        payload.setDateId(dailyMealPlan.getDateId());
        payload.setRating(dailyMealPlan.getRating());
        payload.setBreakfast(dailyMealPlan.toPayload(dailyMealPlan.getBreakfast()));
        payload.setDinner(dailyMealPlan.toPayload(dailyMealPlan.getDinner()));
        payload.setLunch(dailyMealPlan.toPayload(dailyMealPlan.getLunch()));
        return payload;
    }


}
