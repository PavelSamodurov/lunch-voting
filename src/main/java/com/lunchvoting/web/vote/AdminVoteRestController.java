package com.lunchvoting.web.vote;

import com.lunchvoting.model.Vote;
import com.lunchvoting.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteRestController {
    static final String REST_URL = "/rest/admin/votes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    VoteService voteService;

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id){
        log.info("get restaurant {}", id);
        return voteService.get(id);
    }

    @GetMapping("/by")
    public List<Vote> getAllVotesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        log.info("get votes on {}", date);
        return voteService.getAllByDate(date);
    }

    @GetMapping("/today")
    public List<Vote> getAllTodayVotes(){
        log.info("get votes on today");
        return voteService.getAllByDate(LocalDate.now());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vote {}", id);
        voteService.delete(id);
    }
}