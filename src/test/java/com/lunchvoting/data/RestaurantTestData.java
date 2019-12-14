package com.lunchvoting.data;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.repository.TestMatchers;

import static com.lunchvoting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID,"Джумбус");

    public static TestMatchers<Restaurant> RESTAURANT_MATCHERS = TestMatchers.useFieldsComparator(Restaurant.class, "lunchMenuList");
}
