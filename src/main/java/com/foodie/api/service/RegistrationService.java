package com.foodie.api.service;

import com.foodie.api.model.dto.RegistrationDto;
import com.foodie.api.model.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public String register(RegistrationDto request) {

        return userService.signUpUser(
                new UserDto(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword()
                )
        );

    }
}
