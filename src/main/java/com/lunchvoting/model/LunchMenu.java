package com.lunchvoting.model;

import java.time.LocalDate;
import java.util.Set;

public class LunchMenu extends AbstractBaseEntity {
    private LocalDate date;

    private Restaurant restaurant;

    Set<Dish> dishes;
}