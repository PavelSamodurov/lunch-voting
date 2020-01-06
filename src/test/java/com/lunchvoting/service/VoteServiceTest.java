package com.lunchvoting.service;

import com.lunchvoting.model.Vote;
import com.lunchvoting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.lunchvoting.RestaurantTestData.*;
import static com.lunchvoting.UserTestData.USER_ID;
import static com.lunchvoting.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService voteService;

    @Test
    void get() {
        Vote actual = voteService.get(VOTE1_ID);
        VOTE_MATCHERS.assertMatch(actual, VOTE1);
    }

    @Test
    void delete() {
        voteService.delete(VOTE1_ID);
        assertThrows(NotFoundException.class, () ->
                voteService.get(VOTE1_ID));
    }

    @Test
    void vote() {
        Vote actual = voteService.vote(USER_ID, RESTAURANT1_ID, ()-> true);
        VOTE_MATCHERS.assertMatch(actual, reVote());
    }

    @Test
    void reVoteNotAllowed() {
        assertThrows(RuntimeException.class, () ->
                voteService.vote(USER_ID, RESTAURANT1_ID, ()-> false));
    }
}