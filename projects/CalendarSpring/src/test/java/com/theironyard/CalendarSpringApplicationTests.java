package com.theironyard;

import com.theironyard.services.EventRepository;
import com.theironyard.services.FavoriteRepository;
import com.theironyard.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CalendarSpringApplication.class)
@WebAppConfiguration
public class CalendarSpringApplicationTests {

	@Autowired
	EventRepository events;

	@Autowired
	UserRepository users;

	@Autowired
	FavoriteRepository favorites;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@Before
	public void before() {
        favorites.deleteAll();
        events.deleteAll();
        users.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                .param("name", "TestUser")
        );

        Assert.assertTrue(users.count() == 1);
    }

    @Test
    public void testAddEvent() throws Exception {
        testLogin();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-event")
                .param("description", "Test event")
                .param("dateTime", LocalDateTime.now().toString())
                .sessionAttr("userName", "TestUser")
        );

        Assert.assertTrue(events.count() == 1);
    }
}
