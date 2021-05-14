package com.foodie.api.service;

import com.foodie.api.model.entities.User;
import com.foodie.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


@Service
@RequestMapping(path="api/v1/user")
@AllArgsConstructor
public class UserService implements UserDetailsService {



    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final com.foodie.api.repository.UserRepository UserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return UserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User User) {
        boolean userExists = UserRepository.findByEmail(User.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(User.getPassword());

        User.setPassword(encodedPassword);

        UserRepository.save(User);



        return "it works";
    }
}
