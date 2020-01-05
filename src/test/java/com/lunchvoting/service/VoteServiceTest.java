package com.lunchvoting.service;

import com.lunchvoting.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.lunchvoting.RestaurantTestData.RESTAURANT1_ID;
import static com.lunchvoting.UserTestData.USER_ID;
import static com.lunchvoting.VoteTestData.VOTE_MATCHERS;
import static com.lunchvoting.VoteTestData.reVote;
import static org.junit.jupiter.api.Assertions.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService voteService;

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