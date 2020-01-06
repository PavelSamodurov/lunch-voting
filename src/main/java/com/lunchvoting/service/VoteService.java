package com.lunchvoting.service;

import com.lunchvoting.model.Vote;
import com.lunchvoting.repository.VoteRepository;
import com.lunchvoting.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static com.lunchvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    public Vote get(int id){
        return voteRepository.get(id)
                .orElseThrow(() -> new NotFoundException("Vote " + id + " is not found"));
    }

    public List<Vote> getAllByDate(LocalDate date){
        return voteRepository.getAllByLunchMenu_Date(date);
    }

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
            delete(vote.getId());
        } else {
            throw new RuntimeException("It is too late, vote can't be changed");
        }
    }

    public void delete(int id){
        checkNotFoundWithId(voteRepository.delete(id), id);
    }

}