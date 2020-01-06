package com.lunchvoting.web.dish;

import com.lunchvoting.DishTestData;
import com.lunchvoting.TestUtil;
import com.lunchvoting.model.Dish;
import com.lunchvoting.service.DishService;
import com.lunchvoting.util.exception.NotFoundException;
import com.lunchvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.lunchvoting.DishTestData.*;
import static com.lunchvoting.RestaurantTestData.*;
import static com.lunchvoting.TestUtil.readFromJsonMvcResult;
import static com.lunchvoting.UserTestData.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminDishRestControllerTest extends AbstractControllerTest {

    @Autowired
    DishService dishService;

    public AdminDishRestControllerTest() {
        super(AdminDishRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(DISH1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> DISH_MATCHERS.assertMatch(readFromJsonMvcResult(result, Dish.class), DISH1));
    }

    @Test
    void addDish() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(doPost("restaurants/" + RESTAURANT1_ID).jsonBody(newDish).basicAuth(ADMIN))
                .andExpect(status().isCreated());

        Dish created = TestUtil.readFromJson(action, Dish.class);
        Integer newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHERS.assertMatch(created, newDish);
        DISH_MATCHERS.assertMatch(dishService.get(newId), newDish);
    }

    @Test
    void deleteDish() throws Exception {
        perform(doDelete(DISH1_ID).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> dishService.get(DISH1_ID));
    }

    @Test
    void updateDish() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(doPut(DISH1_ID).jsonBody(updated).basicAuth(ADMIN))
                .andExpect(status().isNoContent());

        DISH_MATCHERS.assertMatch(dishService.get(DISH1_ID), updated);
    }
}