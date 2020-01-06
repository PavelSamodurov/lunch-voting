package com.lunchvoting.web.vote;

import com.lunchvoting.model.Vote;
import com.lunchvoting.service.VoteService;
import com.lunchvoting.util.exception.NotFoundException;
import com.lunchvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.time.LocalDate;

import static com.lunchvoting.TestUtil.readFromJsonMvcResult;
import static com.lunchvoting.UserTestData.ADMIN;
import static com.lunchvoting.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminVoteRestControllerTest extends AbstractControllerTest {

    @Autowired
    VoteService voteService;

    public AdminVoteRestControllerTest() {
        super(AdminVoteRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(VOTE1_ID).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> VOTE_MATCHERS.assertMatch(readFromJsonMvcResult(result, Vote.class), VOTE1));
    }

    @Test
    void getAllVotesByDate() throws Exception {
        perform(doGet("/by?date={date}", LocalDate.now()).basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHERS.contentJson(VOTES_LIST_ON_TODAY));
    }

    @Test
    void getAllTodayVotes() throws Exception {
        perform(doGet("/today").basicAuth(ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHERS.contentJson(VOTES_LIST_ON_TODAY));
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(VOTE1_ID).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.get(VOTE1_ID));
    }
}