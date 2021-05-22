package com.foodie.api.service;

import java.util.Optional;

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

  public static UserDto toPayload(User user) {
    UserDto payload = new UserDto();
    payload.setId(user.getId());
    payload.setEmail(user.getEmail());
    payload.setFirstName(user.getFirstName());
    payload.setLastName(user.getLastName());
    payload.setGender(user.getGender());
    payload.setHeight(user.getHeight());
    payload.setWeight(user.getWeight());
    return payload;
  }
}
