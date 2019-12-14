package com.lunchvoting.repository;

import com.lunchvoting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant getByName(String restaurantName);
}
