package com.foodie.api.service;

import com.foodie.api.model.dto.RegistrationDto;
import com.foodie.api.model.entities.User;
import com.foodie.api.model.entities.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService UserService;
    //Optional functionality to be implemented
    private final EmailValidatorService emailValidator;

    public String register(RegistrationDto request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("Email not valid!");
        }
        return UserService.signUpUser(
                new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
}

