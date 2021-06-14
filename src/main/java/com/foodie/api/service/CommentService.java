package com.foodie.api.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.CommentDto;
import com.foodie.api.model.dto.RecipeDto;
import com.foodie.api.model.entities.Comment;
import com.foodie.api.repository.CommentRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeService recipeService;

    public Collection<CommentDto> getAll(Long recipeId) {
        List<Comment> comments = commentRepository.findByRecipeIdOrderByDatetimeDesc(recipeId);
        return comments.stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toList());
    }

    public CommentDto save(Long recipeId, CommentDto payload) {
        RecipeDto recipe = recipeService.getRecipe(recipeId);
        payload.setRecipe(recipe);
        Comment comment = fromPayload(payload);
        comment = commentRepository.save(comment);
        return toPayload(comment);
    }

    public CommentDto update(Long recipeId, Long id, CommentDto payload) {
        Optional<Comment> comment = commentRepository.findById(id);

        if (comment.isPresent()) {
            Comment c = comment.get();
            c = fromPayload(payload);
            c.setId(id);
            c = commentRepository.save(c);
            return toPayload(c);
        }
        throw new RuntimeException("Comment with id " + id + " does not exist!");
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    public static Comment fromPayload(CommentDto payload) {
        Comment comment = new Comment();
        if (payload.getId() != null) comment.setId(payload.getId());
        comment.setDatetime(LocalDateTime.now());
        comment.setComment(payload.getComment());
        comment.setUser(UserService.fromPayload(payload.getUser()));
        comment.setRecipe(RecipeService.fromPayload(payload.getRecipe()));
        return comment;
    }

    public static CommentDto toPayload(Comment comment) {
        CommentDto payload = new CommentDto();
        payload.setId(comment.getId());
        payload.setDatetime(comment.getDatetime());
        payload.setComment(comment.getComment());
        payload.setUser(UserService.toPayload(comment.getUser()));
        payload.setRecipe(RecipeService.toPayload(comment.getRecipe()));
        return payload;
    }
}
