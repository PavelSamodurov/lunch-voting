package com.lunchvoting.service;

import com.lunchvoting.model.Dish;
import com.lunchvoting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.lunchvoting.DishTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService dishService;

    @Test
    void get() {
        Dish actual = dishService.get(DISH1_ID);
        DISH_MATCHERS.assertMatch(actual, DISH1);
    }

    @Test
    void delete() {
        dishService.delete(DISH1_ID);
        assertThrows(NotFoundException.class, () ->
                dishService.get(DISH1_ID));
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        dishService.update(updated);
        DISH_MATCHERS.assertMatch(dishService.get(DISH1_ID), updated);
    }

    @Test
    void create() {
        Dish newDish = getNew();
        Dish created = dishService.create(newDish);
        Integer newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHERS.assertMatch(created, newDish);
        DISH_MATCHERS.assertMatch(dishService.get(newId), newDish);
    }
}