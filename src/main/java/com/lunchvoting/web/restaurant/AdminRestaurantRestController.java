package com.lunchvoting.web.restaurant;

import com.lunchvoting.View;
import com.lunchvoting.model.Dish;
import com.lunchvoting.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.lunchvoting.util.ValidationUtil.assureIdConsistent;
import static com.lunchvoting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController extends AbstractRestaurantController{
    static final String REST_URL = "/rest/admin/restaurants";

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

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {}", restaurant);
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create restaurant {}", restaurant);
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/{restaurantId}/lunches/today", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> addDish(@PathVariable int restaurantId, @RequestBody Dish dish){
        log.info("add dish for restaurant {}", restaurantId);
        dish.setLunchMenu(lunchMenuService.getOrCreateIfNotExistByRestaurantIdAndDate(restaurantId, LocalDate.now()));
        Dish created = dishService.create(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/lunches/today/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/dishes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable int id){
        log.info("delete dish {}", id);
        dishService.delete(id);
    }

    @PutMapping(value = "/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateDish(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int id) {
        assureIdConsistent(dish, id);
        log.info("update dish {}", dish);
        dishService.update(dish);
    }
}
