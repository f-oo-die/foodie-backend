package com.foodie.api.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foodie.api.model.dto.ShoppingListDto;
import com.foodie.api.model.entities.ShoppingList;
import com.foodie.api.repository.ShoppingListRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepo;

    public Collection<ShoppingListDto> getAll() {
        return shoppingListRepo.findAll().stream()
                .map(t -> toPayload(t))
                .collect(Collectors.toList());
    }

    public ShoppingListDto save(ShoppingListDto payload) {
        ShoppingList shoppingList = fromPayload(payload);
        shoppingList = shoppingListRepo.save(shoppingList);

        return toPayload(shoppingList);
    }

    public ShoppingListDto getShoppingList(Long id){
        Optional<ShoppingList> shoppingList = shoppingListRepo.findById(id);
        if (shoppingList.isPresent()){
            return toPayload(shoppingList.get());
        }
        throw new RuntimeException("ShoppingList with id " + id + " does not exist!");
    }

    public ShoppingListDto update (Long id, ShoppingListDto payload){
        getShoppingList(id);

        ShoppingList shoppingList = fromPayload(payload);
        shoppingList.setId(id);
        shoppingList = shoppingListRepo.save(shoppingList);
        return toPayload(shoppingList);
    }

    public void delete(Long id) {
        shoppingListRepo.deleteById(id);
    }
    

    public static ShoppingList fromPayload(ShoppingListDto payload) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(UserService.fromPayload(payload.getUser()));
        shoppingList.setIngredients(payload.getIngredients()
                .stream()
                .map(t -> IngredientService.fromPayloadWithId(t))
                .collect(Collectors.toSet()));
        return shoppingList;
    }

    public static ShoppingListDto toPayload(ShoppingList shoppingList){
        ShoppingListDto payload = new ShoppingListDto();
        payload.setId(shoppingList.getId());
        payload.setUser(UserService.toPayload(shoppingList.getUser()));
        payload.setIngredients(shoppingList.getIngredients()
                .stream()
                .map(t -> IngredientService.toPayload(t))
                .collect(Collectors.toSet()));
        return payload;
    }
}

