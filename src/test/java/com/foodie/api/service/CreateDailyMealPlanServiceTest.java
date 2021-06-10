// ** Commented out because the counter entity was afterwards deleted and daily meal plan creation changed **

// package com.foodie.api.service;

// import com.foodie.api.model.dto.CounterDto;
// import com.foodie.api.model.dto.RecipeDto;
// import com.foodie.api.model.entities.Recipe;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertNotNull;

// class CreateDailyMealPlanServiceTest {

//     CreateDailyMealPlanService sut;

//     @BeforeEach
//     void setUp() {
//         this.sut = new CreateDailyMealPlanService(null);
//     }

//     private RecipeDto generateRecipe() {
//         CounterDto counterDto = new CounterDto();
//         counterDto.setCount(5);

//         Recipe recipe = new Recipe();
//         recipe.setTitle("Random Recipe");

//         RecipeDto recipeDto = new RecipeDto();

//         recipeDto.setId(recipe.getId());
//         recipeDto.setTitle(recipe.getTitle());
//         recipeDto.setPreparation(recipe.getPreparation());
//         recipeDto.setNumOfCalories(recipe.getNumOfCalories());
//         recipeDto.setTypeOfMeal(recipe.getTypeOfMeal());
//         recipeDto.setCalorieStatus(recipe.getCalorieStatus());
//         recipeDto.setRecipeCount(counterDto);
//         return recipeDto;
//     }

//     private List<RecipeDto> generateRecipeList() {
//         List<RecipeDto> recipes = new ArrayList<>();
//         recipes.add(generateRecipe());
//         recipes.add(generateRecipe());
//         recipes.add(generateRecipe());
//         recipes.add(generateRecipe());
//         recipes.add(generateRecipe());
//         return recipes;
//     }

//     @Test
//     void chooseMeal() {
//         RecipeDto recipeDto = sut.chooseMeal(generateRecipeList(), null);

//         assertNotNull(recipeDto);
//     }
// }
