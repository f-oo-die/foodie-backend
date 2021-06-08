package com.foodie.api.model.dto;


import com.foodie.api.model.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String username;
    private Long id;
    private UserRole userRole;
}
