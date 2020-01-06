package com.lunchvoting.web.lunchmenu;

import com.lunchvoting.service.LunchMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = AdminLunchMenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminLunchMenuRestController {
    static final String REST_URL = "/rest/admin/lunches";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LunchMenuService lunchMenuService;

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete lunch menu {}", id);
        lunchMenuService.delete(id);
    }
}
