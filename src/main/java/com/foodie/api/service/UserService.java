package com.foodie.api.service;

import com.foodie.api.model.dto.UserDto;

import com.foodie.api.model.entities.User;
import com.foodie.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signUpUser(UserDto userDto){

        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());

        userDto.setPassword(encodedPassword);
        User user = fromPayload(userDto);
        user = userRepository.save(user);

        return "Registered!";
    }
    public static User fromPayload(UserDto payload){
        User user = new User();
        user.setFirstName(payload.getFirstName());
        user.setLastName(payload.getLastName());
        user.setEmail(payload.getEmail());
        user.setPassword(payload.getPassword());
        user.setHeight(180);
        user.setWeight(75);
        return user;
    }

}
