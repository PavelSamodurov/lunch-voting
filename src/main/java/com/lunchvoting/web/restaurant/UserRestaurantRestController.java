package com.lunchvoting.web.restaurant;

import com.lunchvoting.model.LunchMenu;
import com.lunchvoting.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id){
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAll(){
        return super.getAll();
    }

    @Override
    @GetMapping("/by")
    public Restaurant getByName(String name){
        return super.getByName(name);
    }

    @GetMapping("/lunches")
    public List<LunchMenu> getAllLunchesOnToday(){
        log.info("get lunches on today");
        return lunchMenuService.getAllByDate(LocalDate.now());
    }

    @GetMapping("/lunches/by")
    public List<LunchMenu> getAllLunchesByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        log.info("get lunches on {}", date);
        return lunchMenuService.getAllByDate(date);
    }

    @GetMapping("/{id}/lunches")
    public List<LunchMenu> getAllLunchesByRestaurantId(@PathVariable int id){
        log.info("get lunches for restaurant {}", id);
        return lunchMenuService.getAllByRestaurantId(id);
    }

    @GetMapping("/{id}/lunches/today")
    public LunchMenu getLunchByRestaurantOnToday(@PathVariable int id){
        log.info("get lunches for restaurant {} on today", id);
        return lunchMenuService.getByRestaurantIdAndDate(id, LocalDate.now());
    }

    @GetMapping("/{id}/lunches/by")
    public LunchMenu getLunchByRestaurantAndDate(@PathVariable int id,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        log.info("get lunches for restaurant {} on {}", id, date);
        return lunchMenuService.getByRestaurantIdAndDate(id, date);
    }
}
