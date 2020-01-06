package com.lunchvoting;

import com.lunchvoting.model.Vote;

import java.util.List;

import static com.lunchvoting.LunchMenuTestData.LUNCH_MENU1;
import static com.lunchvoting.LunchMenuTestData.LUNCH_MENU2;
import static com.lunchvoting.UserTestData.ADMIN;
import static com.lunchvoting.UserTestData.USER;
import static com.lunchvoting.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE1_ID = START_SEQ + 40;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, USER, LUNCH_MENU1);
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, ADMIN, LUNCH_MENU2);

    public static final List<Vote> VOTES_LIST_ON_TODAY = List.of(VOTE1, VOTE2);

    public static Vote reVote() {
       return new Vote(START_SEQ + 42, USER, LUNCH_MENU2);
    }

    public static TestMatchers<Vote> VOTE_MATCHERS = TestMatchers.useFieldsComparator(Vote.class, "user", "lunchMenu");
}