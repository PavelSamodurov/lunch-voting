package com.lunchvoting.repository;

import com.lunchvoting.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.lunchvoting.data.RestaurantTestData.RESTAURANT1;
import static com.lunchvoting.data.RestaurantTestData.RESTAURANT_MATCHERS;

class RestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Test
    void get() {
        Restaurant restaurant = restaurantRepository.findByName("Джумбус").get();
        RESTAURANT_MATCHERS.assertMatch(restaurant, RESTAURANT1);
    }
}