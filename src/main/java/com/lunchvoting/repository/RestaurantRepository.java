package com.lunchvoting.repository;

import com.lunchvoting.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepository {
    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    public Restaurant get(String restaurantName){
        return crudRestaurantRepository.getByName(restaurantName);
    }
}
