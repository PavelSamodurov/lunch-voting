package com.lunchvoting.model;

import java.time.LocalDate;
import java.util.Map;

public class LunchMenu extends AbstractBaseEntity {
    private LocalDate date;

    private Restaurant restaurant;

    Map<String,Long> dishes;
}