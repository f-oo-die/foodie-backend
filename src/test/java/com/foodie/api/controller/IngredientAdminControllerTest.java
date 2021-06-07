package com.foodie.api.controller;

import static com.foodie.api.builder.ObjectTestBuilder.INGREDIENT_ID;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.foodie.api.builder.ObjectTestBuilder.buildIngredientDto;

import com.foodie.api.controller.admin.IngredientAdminController;
import com.foodie.api.model.dto.IngredientDto;
import com.foodie.api.service.IngredientService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = IngredientAdminController.class)
public class IngredientAdminControllerTest {
  
  @MockBean
  private IngredientService ingredientService;

  @Autowired
  private MockMvc mvc;

  @Test
  public void getIngredientSuccess() throws Exception {
    mvc.perform(get("/admin/ingredients/" + INGREDIENT_ID))
        .andExpect(status().isOk());

    verify(ingredientService).getIngredient(INGREDIENT_ID);
  }

  @Test
  public void saveIngredientSuccess() throws Exception {
    IngredientDto ingredient = buildIngredientDto();
    mvc.perform(post("/admin/ingredients"))
        .andExpect(status().isOk());

    verify(ingredientService).save(ingredient);
  }
    

  @Test
  public void deleteIngredientSuccess() throws Exception {
    mvc.perform(delete("/admin/ingredients/" + INGREDIENT_ID))
        .andExpect(status().isNoContent());

    verify(ingredientService).delete(INGREDIENT_ID);
  }

}
