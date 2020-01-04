package com.lunchvoting.service;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.model.Restaurant;
import com.lunchvoting.repository.LunchMenuRepository;
import com.lunchvoting.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class LunchMenuService {

    @Autowired
    LunchMenuRepository lunchMenuRepository;

    public LunchMenu get(int id){
        return lunchMenuRepository.get(id)
                .orElseThrow(()-> new NotFoundException("Lunch menu with id=" + id + " is not found"));
    }

    public void delete(int id){
        checkNotFoundWithId(lunchMenuRepository.delete(id), id);
    }

    public LunchMenu create(LocalDate date, Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        LunchMenu lunchMenu = new LunchMenu(date, restaurant);
        return lunchMenuRepository.save(lunchMenu);
    }

    public LunchMenu getByRestaurantAndDate(LocalDate date, Restaurant restaurant){
        return lunchMenuRepository.getByRestaurantAndDate(restaurant, date)
                .orElseThrow(()-> new NotFoundException("Lunch menu for " + restaurant.getName() + " on " + date + " is not found"));
    }

    public List<LunchMenu> getAllByRestaurant(Restaurant restaurant){
        return lunchMenuRepository.getAllByRestaurant(restaurant);
    }

    public List<LunchMenu> getAllByDate(LocalDate date){
        return lunchMenuRepository.getAllByDate(date);
    }

}
