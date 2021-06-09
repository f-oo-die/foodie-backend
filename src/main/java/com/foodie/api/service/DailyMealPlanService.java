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

    public List<DailyMealPlanDto> getDailyMealPlanList(Long userId){
        List<DailyMealPlan> dailyMealPlan= dailyMealPlanRepo.findDailyMealPlanofUser(userId);
        return dailyMealPlan.stream().map(t -> toPayload(t)).collect(Collectors.toList());
    }

    public DailyMealPlanDto getDailyMealPlan(Long userId, Long id){
        Optional<DailyMealPlan> dailyMealPlan = dailyMealPlanRepo.findByUserAndId(userId, id);
        if (dailyMealPlan.isPresent()){
            return toPayload(dailyMealPlan.get());
        }
        throw new RuntimeException("DailyMealPlan with id" + id + "is not present!");
    }

    public DailyMealPlanDto getLatestDailyMealPlan(Long userId){
        Optional<DailyMealPlan> dailyMealPlan = dailyMealPlanRepo.findLatestDailyMealPlanofUser(userId);
        if (dailyMealPlan.isPresent()){
            return toPayload(dailyMealPlan.get());
        }
        throw new RuntimeException("Error in getting the latest daily meal plan of user with id " + userId);
    }

    public DailyMealPlanDto create(Long userId){
        UserDto user = userService.getUser(userId);
        DailyMealPlanDto dailyMealPlan = new DailyMealPlanDto();
        dailyMealPlan = createDailyMealPlanService.createDailyMealPlan(user);
        dailyMealPlan = save(dailyMealPlan, user);
        return dailyMealPlan;
    }

    public DailyMealPlanDto save(DailyMealPlanDto payload, UserDto user) {
        DailyMealPlan dailyMealPlan = fromPayload(payload, user);
        dailyMealPlan.setUser(UserService.fromPayload(user));
        dailyMealPlan = dailyMealPlanRepo.save(dailyMealPlan);

        return toPayload(dailyMealPlan);
    }

    private DailyMealPlan fromPayload(DailyMealPlanDto payload, UserDto user){
        DailyMealPlan dailyMealPlan = new DailyMealPlan();
        dailyMealPlan.setDate(LocalDate.now());
        dailyMealPlan.setBreakfast(recipeService.updateCount(payload.getBreakfast()));
        dailyMealPlan.setLunch(recipeService.updateCount(payload.getLunch()));
        dailyMealPlan.setDinner(recipeService.updateCount(payload.getDinner()));
        return dailyMealPlan;
    }

    public static DailyMealPlanDto toPayload(DailyMealPlan dailyMealPlan){
        DailyMealPlanDto payload = new DailyMealPlanDto();
        payload.setId(dailyMealPlan.getId());
        payload.setDate(dailyMealPlan.getDate());
        payload.setBreakfast(RecipeService.toPayload(dailyMealPlan.getBreakfast()));
        payload.setLunch(RecipeService.toPayload(dailyMealPlan.getLunch()));
        payload.setDinner(RecipeService.toPayload(dailyMealPlan.getDinner()));
        return payload;
    }
}
