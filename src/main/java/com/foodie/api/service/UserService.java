package com.foodie.api.service;

import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.UserDto;
import com.foodie.api.model.entities.User;
import com.foodie.api.model.entities.UserRole;
import com.foodie.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Value("${ADMINUSERNAME}")
    private String adminUsername;
    @Value("${ADMINPASSWORD}")
    private String adminPassword;
    @Value("${ADMINFIRSTNAME}")
    private String adminFirstName;
    @Value("${ADMINLASTNAME}")
    private String adminLastName;

    @PostConstruct
    public void init() {
        //environment variable -- cors prof. config
        Optional<User> user = userRepository.findByEmail(adminUsername);
        if (!user.isPresent()) {
            User adminUser = new User();
            adminUser.setEmail(adminUsername);
            adminUser.setPassword(adminPassword);
            adminUser.setFirstName(adminFirstName);
            adminUser.setLastName(adminLastName);
            adminUser.setHeight(175);
            adminUser.setWeight(68);
            adminUser.setProfileImageUrl("https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg");
            adminUser.setUserRole(UserRole.ADMIN);
            userRepository.save(adminUser);
        }
    }

    public UserDto getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) return toPayload(user.get());
        throw new RuntimeException("User with id " + id + " does not exist!");
    }

    public UserDto update(Long id, UserDto payload) {
        getUser(id);

        User user = fromPayload(payload);
        user.setId(id);
        user = userRepository.save(user);
        return toPayload(user);
    }

    public static User fromPayload(UserDto payload) {
        User user = new User();
        if (payload.getId() != null) user.setId(payload.getId());
        user.setEmail(payload.getEmail());
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        if (payload.getWeight() != null) user.setWeight(payload.getWeight());
        if (payload.getHeight() != null) user.setHeight(payload.getHeight());
        if (payload.getProfileImageUrl() != null) user.setProfileImageUrl(payload.getProfileImageUrl());
        user.setPassword(payload.getPassword());
        user.setHeight(payload.getHeight());
        user.setWeight(payload.getWeight());
        user.setProfileImageUrl(payload.getProfileImageUrl());
        user.setNutritionIssues(
                payload.getNutritionIssues().stream()
                        .map((t) -> NutritionIssueService.fromPayload(t))
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
        payload.setProfileImageUrl(user.getProfileImageUrl());
        payload.setNutritionIssues(
                user.getNutritionIssues().stream()
                        .map((t) -> NutritionIssueService.toPayload(t))
                        .collect(Collectors.toSet())
        );
        return payload;
    }
}
