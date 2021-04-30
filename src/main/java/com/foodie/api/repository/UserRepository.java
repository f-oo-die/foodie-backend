package com.foodie.api.repository;

import com.foodie.api.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // write query
    // @Query
    ArrayList<User> findByFirstName(String FirstName);
    // write query
    // @Query
    ArrayList<User> findAll();
}
