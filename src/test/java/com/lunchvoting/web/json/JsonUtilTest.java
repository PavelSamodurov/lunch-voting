package com.lunchvoting.web.json;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.model.User;
import com.lunchvoting.testdata.UserTestData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.lunchvoting.testdata.RestaurantTestData.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(RESTAURANT1);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RESTAURANT_MATCHERS.assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(RESTAURANTS);
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        RESTAURANT_MATCHERS.assertMatch(restaurants, RESTAURANTS);
    }

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(UserTestData.USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = JsonUtil.writeAdditionProps(UserTestData.USER, "password", "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }

}
