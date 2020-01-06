package com.lunchvoting.web.restaurant;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.service.VoteService;
import com.lunchvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.lunchvoting.RestaurantTestData.*;
import static com.lunchvoting.TestUtil.readFromJsonMvcResult;
import static com.lunchvoting.UserTestData.ADMIN;
import static com.lunchvoting.UserTestData.USER;
import static com.lunchvoting.VoteTestData.VOTE1_ID;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserRestaurantRestControllerTest extends AbstractControllerTest {

    @Autowired
    private VoteService voteService;

    public UserRestaurantRestControllerTest() {
        super(UserRestaurantRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(RESTAURANT1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> RESTAURANT_MATCHERS.assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT1));
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHERS.contentJson(RESTAURANTS));
    }

    @Test
    void getByName() throws Exception {
        perform(doGet("by?name={name}", RESTAURANT1.getName()).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHERS.contentJson(RESTAURANT1));
    }

    @Test
    void vote() throws Exception {
        voteService.delete(VOTE1_ID);
        perform(doPost(RESTAURANT1_ID + "/vote").basicAuth(USER))
                .andExpect(status().isCreated());
    }
}