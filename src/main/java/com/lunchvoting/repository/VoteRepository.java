package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.model.Restaurant;
import com.lunchvoting.model.User;
import com.lunchvoting.model.Vote;
import com.lunchvoting.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class VoteRepository {
    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudLunchMenuRepository crudLunchMenuRepository;

    public Vote save(int userId, int restaurantId){
        Vote vote = new Vote();
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setLunchMenu(crudLunchMenuRepository.getByRestaurant_IdAndDate(restaurantId,
                LocalDate.now()).orElseThrow(() -> new NotFoundException("Lunch menu for " + restaurantId + " on today is not found")));
        return crudVoteRepository.save(vote);
    }

    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    public Optional<Vote> getByUser_IdAndLunchMenu_Date(int userId, LocalDate date){
        return crudVoteRepository.getByUser_IdAndLunchMenu_Date(userId, date);
    }

    public List<Vote> getAllByLunchMenu_RestaurantAndLunchMenu_Date(Restaurant restaurant, LocalDate date){
        return crudVoteRepository.getAllByLunchMenu_RestaurantAndLunchMenu_Date(restaurant, date);
    }
}
