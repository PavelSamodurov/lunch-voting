package com.lunchvoting.repository;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.util.exception.NotFoundException;
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
        if (!restaurant.isNew()){
            get(restaurant.getId()).orElseThrow(() -> new NotFoundException("Not found entity with id=" + restaurant.getId()));
        }
        return crudRestaurantRepository.save(restaurant);
    }

    public boolean delete(int id){
        return crudRestaurantRepository.delete(id) != 0;
    }

    public Optional<Restaurant> get(int id){
        return crudRestaurantRepository.getById(id);
    }

    public Optional<Restaurant> getByName(String restaurantName){
        return crudRestaurantRepository.getByName(restaurantName);
    }

    public List<Restaurant> getAll(){
        return crudRestaurantRepository.findAll();
    }
}
