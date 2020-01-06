package com.lunchvoting.web.restaurant;

import com.lunchvoting.AuthorizedUser;
import com.lunchvoting.model.Restaurant;
import com.lunchvoting.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping(value = "/{restaurantId}/vote")
    public ResponseEntity<Vote> vote(@PathVariable int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser){
        log.info("vote for restaurant {}", restaurantId);
        Vote vote = voteService.vote(authUser.getId(), restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/vote")
                .buildAndExpand(restaurantId).toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }
}
