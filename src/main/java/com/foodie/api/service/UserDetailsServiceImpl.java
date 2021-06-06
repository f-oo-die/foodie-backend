package com.foodie.api.service;

import com.foodie.api.model.entities.User;
import com.foodie.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.singletonList;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                "Found with email :" + email));

        return new org.springframework.security.
                core.userdetails.User(user.getEmail(), user.getPassword(),
                getAuthorities("USER"));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

}
