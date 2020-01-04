package com.lunchvoting.service;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import java.util.List;

import static com.lunchvoting.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    void delete() throws Exception {
        restaurantService.delete(RESTAURANT1_ID);
        assertThrows(NotFoundException.class, () ->
                restaurantService.get(RESTAURANT1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.delete(1));
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getNew();
        Restaurant created = restaurantService.create(newRestaurant);
        Integer newId = created.getId();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHERS.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHERS.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void get() throws Exception {
        Restaurant actual = restaurantService.get(RESTAURANT1_ID);
        RESTAURANT_MATCHERS.assertMatch(actual, RESTAURANT1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                restaurantService.get(1));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getUpdated();
        restaurantService.update(updated);
        RESTAURANT_MATCHERS.assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }

    @Test
    void updateNotFound() throws Exception {
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(1);
        NotFoundException e = assertThrows(NotFoundException.class, () -> restaurantService.update(newRestaurant));
        assertEquals("Not found entity with id=" + newRestaurant.getId(), e.getMessage());
    }

    @Test
    void getAll() throws Exception {
        List<Restaurant> restaurants = restaurantService.getAll();
        RESTAURANT_MATCHERS.assertMatch(restaurants, RESTAURANTS);
    }

    @Test
    void getByName() {
        Restaurant restaurant = restaurantService.getByName("Джумбус");
        RESTAURANT_MATCHERS.assertMatch(restaurant, RESTAURANT1);
    }


    @Test
    void createWithException() throws Exception {
        validateRootCause(() -> restaurantService.create(new Restaurant(null,null)),ConstraintViolationException.class);
    }
}