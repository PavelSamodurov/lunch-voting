package com.lunchvoting.web.lunchmenu;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.service.LunchMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserLunchMenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserLunchMenuRestController {
    static final String REST_URL = "/rest/lunches";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LunchMenuService lunchMenuService;

    @GetMapping("/{id}")
    public LunchMenu get(@PathVariable int id){
        log.info("get lunch menu {}", id);
        return lunchMenuService.get(id);
    }

    @GetMapping("/today")
    public List<LunchMenu> getAllLunchesOnToday(){
        log.info("get lunches on today");
        return lunchMenuService.getAllByDate(LocalDate.now());
    }

    @GetMapping("/by")
    public List<LunchMenu> getAllLunchesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        log.info("get lunches on {}", date);
        return lunchMenuService.getAllByDate(date);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public List<LunchMenu> getAllLunchesByRestaurantId(@PathVariable int restaurantId){
        log.info("get lunches for restaurant {}", restaurantId);
        return lunchMenuService.getAllByRestaurantId(restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}/today")
    public LunchMenu getLunchByRestaurantOnToday(@PathVariable int restaurantId){
        log.info("get lunches for restaurant {} on today", restaurantId);
        return lunchMenuService.getByRestaurantIdAndDate(restaurantId, LocalDate.now());
    }

    @GetMapping("/restaurants/{restaurantId}/by")
    public LunchMenu getLunchByRestaurantAndDate(@PathVariable int restaurantId,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        log.info("get lunches for restaurant {} on {}", restaurantId, date);
        return lunchMenuService.getByRestaurantIdAndDate(restaurantId, date);
    }
}
