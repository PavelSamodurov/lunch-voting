package com.lunchvoting.web.lunchmenu;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static com.lunchvoting.LunchMenuTestData.*;
import static com.lunchvoting.RestaurantTestData.*;
import static com.lunchvoting.TestUtil.readFromJsonMvcResult;
import static com.lunchvoting.UserTestData.ADMIN;
import static com.lunchvoting.UserTestData.USER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserLunchMenuRestControllerTest extends AbstractControllerTest{

    public UserLunchMenuRestControllerTest() {
        super(UserLunchMenuRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(LUNCH_MENU1_ID).basicAuth(USER))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> LUNCH_MENU_MATCHERS.assertMatch(readFromJsonMvcResult(result, LunchMenu.class), LUNCH_MENU1));
    }

    @Test
    void getAllLunchesOnToday() throws Exception {
        perform(doGet("/today").basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LUNCH_MENU_MATCHERS.contentJson(LUNCH_MENU_LIST_ON_TODAY));
    }

    @Test
    void getAllLunchesByDate() throws Exception {
        perform(doGet("by?date={date}", LocalDate.now()).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LUNCH_MENU_MATCHERS.contentJson(LUNCH_MENU_LIST_ON_TODAY));
    }

    @Test
    void getAllLunchesByRestaurantId() throws Exception {
        perform(doGet("/restaurants/{restaurantId}", RESTAURANT1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LUNCH_MENU_MATCHERS.contentJson(LUNCH_MENU_LIST_OF_RESTAURANT1));
    }

    @Test
    void getLunchByRestaurantOnToday() throws Exception {
        perform(doGet("/restaurants/{restaurantId}/today", RESTAURANT1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LUNCH_MENU_MATCHERS.contentJson(LUNCH_MENU1));
    }

    @Test
    void getLunchByRestaurantAndDate() throws Exception {
        perform(doGet("/restaurants/{restaurantId}/by?date={date}", RESTAURANT1_ID, LocalDate.now()).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LUNCH_MENU_MATCHERS.contentJson(LUNCH_MENU1));
    }
}