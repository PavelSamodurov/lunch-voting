package com.lunchvoting.service;

import com.lunchvoting.model.Dish;
import com.lunchvoting.repository.DishRepository;
import com.lunchvoting.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.lunchvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    @Autowired
    DishRepository dishRepository;

    public Dish get(int id){
        return dishRepository.get(id)
                .orElseThrow(()-> new NotFoundException("Lunch menu with id=" + id + " is not found"));
    }

    public void delete(int id){
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        dish.setLunchMenu(get(dish.getId()).getLunchMenu());
        checkNotFoundWithId(dishRepository.save(dish), dish.getId());
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish);
    }

}
