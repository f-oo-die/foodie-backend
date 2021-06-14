package com.foodie.api.controller;

import java.util.Collection;

import com.foodie.api.model.dto.CommentDto;
import com.foodie.api.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @GetMapping("/{recipeId}")
    public ResponseEntity<Collection<CommentDto>> getAll(
            @PathVariable Long recipeId
    ) {
        Collection<CommentDto> allComments = service.getAll(recipeId);

        return ResponseEntity.status(HttpStatus.OK).body(allComments);
    }

    @PostMapping("/{recipeId}")
    public ResponseEntity<CommentDto> save(
            @PathVariable Long recipeId,
            @RequestBody CommentDto comment
    ) {
        CommentDto result = service.save(recipeId, comment);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{recipeId}/{id}")
    public ResponseEntity<CommentDto> update(
            @PathVariable Long recipeId,
            @PathVariable Long id,
            @RequestBody CommentDto comment
    ) {
        CommentDto result = service.update(recipeId, id, comment);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
