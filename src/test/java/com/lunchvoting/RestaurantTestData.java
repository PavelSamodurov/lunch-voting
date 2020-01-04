package com.lunchvoting;

import com.lunchvoting.model.Restaurant;

import java.util.List;

import static com.lunchvoting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID,"Джумбус");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1,"Сабор де ла Вида Ресторан");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2,"Lure Oyster Bar");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3,"WE Cidreria");
    public static final Restaurant RESTAURANT5 = new Restaurant(RESTAURANT1_ID + 4,"Megobari");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный ресторан");
    }

    public static TestMatchers<Restaurant> RESTAURANT_MATCHERS = TestMatchers.useFieldsComparator(Restaurant.class, "lunchMenuList");
}