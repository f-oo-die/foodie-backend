package com.foodie.api.controller;

import static com.foodie.api.builder.ObjectTestBuilder.INGREDIENT_ID;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.foodie.api.controller.admin.IngredientAdminController;
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
  public void deleteIngredientSuccess() throws Exception {
    mvc.perform(delete("/admin/ingredients/" + INGREDIENT_ID))
        .andExpect(status().isNoContent());

    verify(ingredientService).delete(INGREDIENT_ID);
  }

}
