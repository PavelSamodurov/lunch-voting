package com.lunchvoting.repository;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.model.Restaurant;
import com.lunchvoting.model.User;
import com.lunchvoting.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class VoteRepository {
    @Autowired
    private CrudVoteRepository crudVoteRepository;

    public Vote save(User user, LunchMenu lunchMenu){
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setLunchMenu(lunchMenu);
        return crudVoteRepository.save(vote);
    }

    public boolean delete(int id) {
        return crudVoteRepository.delete(id) != 0;
    }

    public Optional<Vote> getByUserAndLunchMenu_Date(User user, LocalDate date){
        return crudVoteRepository.getByUserAndLunchMenu_Date(user, date);
    }

    public List<Vote> getAllByLunchMenu_RestaurantAndLunchMenu_Date(Restaurant restaurant, LocalDate date){
        return crudVoteRepository.getAllByLunchMenu_RestaurantAndLunchMenu_Date(restaurant, date);
    }
}
