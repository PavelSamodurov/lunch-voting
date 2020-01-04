package com.lunchvoting.web.restaurant;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.service.LunchMenuService;
import com.lunchvoting.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    LunchMenuService lunchMenuService;

    public Restaurant get(@PathVariable int id){
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    public List<Restaurant> getAll(){
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    public Restaurant getByName(String name){
        log.info("get restaurant {}", name);
        return restaurantService.getByName(name);
    }
}
