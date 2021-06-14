package com.foodie.api.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.model.dto.ShoppingListDto;
import com.foodie.api.model.dto.UserDto;
import com.foodie.api.model.entities.ShoppingList;
import com.foodie.api.repository.ShoppingListRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepo;
    private final UserService userService;

    public Collection<ShoppingListDto> getAll(Long userId) {
        List<ShoppingList> shoppingLists = shoppingListRepo.findShoppingListsOfUser(userId);
        return shoppingLists.stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toList());
    }

    public ShoppingListDto save(Long userId, ShoppingListDto payload) {
        UserDto user = userService.getUser(userId);
        payload.setUser(user);
        ShoppingList dbShoppingList = fromPayload(payload);
        dbShoppingList = shoppingListRepo.save(dbShoppingList);

        return toPayload(dbShoppingList);
    }

    public ShoppingListDto getShoppingList(Long userId, Long id) {
        Optional<ShoppingList> shoppingList = shoppingListRepo.findByIdAndUserId(userId, id);
        if (shoppingList.isPresent()) {
            return toPayload(shoppingList.get());
        }
        throw new RuntimeException("ShoppingList with id " + id + " does not exist!");
    }

    public ShoppingListDto update(Long userId, Long id, ShoppingListDto payload) {
        getShoppingList(userId, id);

        ShoppingList shoppingList = fromPayload(payload);
        shoppingList.setId(id);
        shoppingList = shoppingListRepo.save(shoppingList);
        return toPayload(shoppingList);
    }

    @Transactional
    public void delete(Long userId, Long id) {
        ShoppingListDto shoppingList = getShoppingList(userId, id);
        for (IngredientDto ingredient : shoppingList.getIngredients()) {
            shoppingListRepo.deleteRelationship(id, ingredient.getId());
        }
        shoppingListRepo.deleteByUserIdAndId(userId, id);
    }


    public static ShoppingList fromPayload(ShoppingListDto payload) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setTitle(payload.getTitle());
        shoppingList.setUser(UserService.fromPayload(payload.getUser()));
        shoppingList.setIngredients(payload.getIngredients()
                .stream()
                .map(t -> IngredientService.fromPayload(t))
                .collect(Collectors.toSet()));
        return shoppingList;
    }

    public static ShoppingListDto toPayload(ShoppingList shoppingList) {
        ShoppingListDto payload = new ShoppingListDto();
        payload.setId(shoppingList.getId());
        payload.setTitle(shoppingList.getTitle());
        payload.setUser(UserService.toPayload(shoppingList.getUser()));
        payload.setIngredients(shoppingList.getIngredients()
                .stream()
                .map(t -> IngredientService.toPayload(t))
                .collect(Collectors.toSet()));
        return payload;
    }
}

