package com.lunchvoting.web.dish;

import com.lunchvoting.View;
import com.lunchvoting.model.Dish;
import com.lunchvoting.service.DishService;
import com.lunchvoting.service.LunchMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

import static com.lunchvoting.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {
    static final String REST_URL = "/rest/admin/dishes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DishService dishService;

    @Autowired
    LunchMenuService lunchMenuService;

    @GetMapping("/{id}")
    public Dish getDish(@PathVariable int id){
        log.info("get dish {}", id);
        return dishService.get(id);
    }

    @PostMapping(value = "/restaurants/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> addDish(@PathVariable int restaurantId, @RequestBody Dish dish){
        log.info("add dish for restaurant {}", restaurantId);
        dish.setLunchMenu(lunchMenuService.getOrCreateIfNotExistByRestaurantIdAndDate(restaurantId, LocalDate.now()));
        Dish created = dishService.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable int id){
        log.info("delete dish {}", id);
        dishService.delete(id);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateDish(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int id) {
        assureIdConsistent(dish, id);
        log.info("update dish {}", dish);
        dishService.update(dish);
    }
}
