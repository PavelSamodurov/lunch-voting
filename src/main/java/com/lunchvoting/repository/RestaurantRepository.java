package com.lunchvoting.repository;

import com.lunchvoting.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepository {
    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public Restaurant save(Restaurant restaurant){
        return crudRestaurantRepository.save(restaurant);
    }

    public boolean delete(int id){
        return crudRestaurantRepository.delete(id) != 0;
    }

    public Optional<Restaurant> getByName(String restaurantName){
        return crudRestaurantRepository.getByName(restaurantName);
    }

    public List<Restaurant> getAll(){
        return crudRestaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantsByLunchMenuDate(LocalDate date){
        return crudRestaurantRepository.getRestaurantsByLunchMenuListIs(date);
    }
}
