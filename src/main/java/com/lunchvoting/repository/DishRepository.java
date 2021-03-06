package com.lunchvoting.repository;

import com.lunchvoting.model.Dish;
import com.lunchvoting.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DishRepository {
    @Autowired
    private CrudDishRepository crudDishRepository;

    public Optional<Dish> get(int id) {
        return crudDishRepository.getById(id);
    }

    public Dish save(Dish dish){
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }
}
