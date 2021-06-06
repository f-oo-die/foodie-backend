package com.foodie.api.service;

import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.UserDto;
import com.foodie.api.model.entities.User;
import com.foodie.api.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return toPayload(user.get());
        }
        throw new RuntimeException("User with id " + id + " does not exist!");
    }

    public UserDto update(Long id, UserDto payload){
        getUser(id);

        User user = fromPayload(payload);
        user.setId(id);
        user = userRepository.save(user);
        return toPayload(user);
    }

    public static User fromPayload(UserDto payload){
        User user = new User();
        user.setId(payload.getId());
        user.setEmail(payload.getEmail());
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setPassword(payload.getPassword());
        user.setNutritionIssues(
            payload.getNutritionIssues().stream()
            .map((t) -> NutritionIssueService.fromPayloadWithId(t))
            .collect(Collectors.toSet())
        );
        user.setFavoriteRecipes(
            payload.getFavoriteRecipes().stream()
            .map((t) -> RecipeService.fromPayloadWithId(t))
            .collect(Collectors.toSet())
        );
        return user;
    }
    
    public static UserDto toPayload(User user) {
        UserDto payload = new UserDto();
        payload.setId(user.getId());
        payload.setEmail(user.getEmail());
        payload.setFirstName(user.getFirstName());
        payload.setLastName(user.getLastName());
        payload.setPassword(user.getPassword());
        payload.setHeight(user.getHeight());
        payload.setWeight(user.getWeight());
        payload.setNutritionIssues(
            user.getNutritionIssues().stream()
            .map((t) -> NutritionIssueService.toPayload(t))
            .collect(Collectors.toSet())
        );
        payload.setFavoriteRecipes(
            user.getFavoriteRecipes().stream()
            .map((t) -> RecipeService.toPayload(t))
            .collect(Collectors.toSet())
        );
        return payload;
    }
}
