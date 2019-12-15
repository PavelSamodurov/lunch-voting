package com.lunchvoting.repository;

import com.lunchvoting.model.Dish;
import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepository {
    @Autowired
    private CrudDishRepository crudDishRepository;

    Dish save(Dish dish, LunchMenu lunchMenu){
        dish.setLunchMenu(lunchMenu);
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    List<Dish> findAllByLunchMenu_Date(LocalDate date){
        return crudDishRepository.findAllByLunchMenu_Date(date);
    }

    List<Dish> findAllByLunchMenu_DateAndLunchMenu_Restaurant(LocalDate date, Restaurant restaurant){
        return crudDishRepository.findAllByLunchMenu_DateAndLunchMenu_Restaurant(date,restaurant);
    }
}
