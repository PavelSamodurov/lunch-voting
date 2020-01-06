package com.lunchvoting.web.lunchmenu;

import com.lunchvoting.service.LunchMenuService;
import com.lunchvoting.util.exception.NotFoundException;
import com.lunchvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.lunchvoting.testdata.LunchMenuTestData.LUNCH_MENU1_ID;
import static com.lunchvoting.testdata.UserTestData.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminLunchMenuRestControllerTest extends AbstractControllerTest {

    @Autowired
    private LunchMenuService lunchMenuService;

    public AdminLunchMenuRestControllerTest() {
        super(AdminLunchMenuRestController.REST_URL);
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(LUNCH_MENU1_ID).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> lunchMenuService.get(LUNCH_MENU1_ID));
    }
}