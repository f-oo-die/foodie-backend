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
    public void init(){
        //environment variable -- cors prof. config
        Optional<User> user = userRepository.findByEmail(adminUsername);
        if(!user.isPresent()){
            User adminUser = new User();
            adminUser.setEmail(adminUsername);
            adminUser.setPassword(adminPassword);
            adminUser.setFirstName(adminFirstName);
            adminUser.setLastName(adminLastName);
            adminUser.setHeight(175);
            adminUser.setWeight(68);
            adminUser.setProfileImageUrl("https://image.dnevnik.hr/media/images/1920x1080/Mar2020/61855053-lzn.jpg");
            adminUser.setUserRole(UserRole.ADMIN);
            userRepository.save(adminUser);
        }
    }

    public UserDto getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            if(user.get().getHeight() == null && user.get().getWeight() == null){
                return toPartialPayload(user.get());
            }
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
        if (payload.getId() != null) user.setId(payload.getId());
        user.setEmail(payload.getEmail());
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
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
    
    private UserDto toPartialPayload(User user) {
        UserDto payload = new UserDto();
        payload.setId(user.getId());
        payload.setEmail(user.getEmail());
        payload.setFirstName(user.getFirstName());
        payload.setLastName(user.getLastName());
        payload.setPassword(user.getPassword());
        payload.setProfileImageUrl(user.getProfileImageUrl());
        payload.setNutritionIssues(
            user.getNutritionIssues().stream()
            .map((t) -> NutritionIssueService.toPayload(t))
            .collect(Collectors.toSet())
        );
        return payload;
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
