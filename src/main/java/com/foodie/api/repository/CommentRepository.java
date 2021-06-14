package com.foodie.api.repository;

import java.util.List;

import com.foodie.api.model.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByRecipeIdOrderByDatetimeDesc(Long recipeId);

}
