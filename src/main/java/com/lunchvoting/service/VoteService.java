package com.lunchvoting.service;

import com.lunchvoting.model.Vote;
import com.lunchvoting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.function.BooleanSupplier;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    public Vote vote(int userId, int restaurantId){
        BooleanSupplier isAfterEleven = () -> LocalTime.now().isBefore(LocalTime.of(11, 0));
        return vote(userId, restaurantId, isAfterEleven);
    }

    public Vote vote(int userId, int restaurantId, BooleanSupplier reVoteCondition){
        Optional<Vote> todayVote = voteRepository.getByUser_IdAndLunchMenu_Date(userId, LocalDate.now());
        todayVote.ifPresent(vote -> deleteVote(vote, reVoteCondition));
        return voteRepository.save(userId, restaurantId);
    }

    private void deleteVote(Vote vote, BooleanSupplier deleteCondition){
        if(deleteCondition.getAsBoolean()){
            voteRepository.delete(vote.getId());
        } else {
            throw new RuntimeException("It is too late, vote can't be changed");
        }
    }

}