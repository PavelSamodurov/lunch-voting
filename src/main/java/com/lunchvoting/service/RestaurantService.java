package com.lunchvoting.service;

import com.lunchvoting.model.Restaurant;
import com.lunchvoting.repository.RestaurantRepository;
import com.lunchvoting.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.lunchvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant get(int id) {
        return restaurantRepository.get(id)
                .orElseThrow(()-> new NotFoundException("Restaurant with id" + id + " is not found"));
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        restaurantRepository.save(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getByName(String name){
        return restaurantRepository.getByName(name)
                .orElseThrow(()-> new NotFoundException("Restaurant " + name + " is not found"));
    }

//    public Restaurant getWith(int id) {
//        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
//    }

}
