package com.theironyard.controllers;

import com.theironyard.entities.Event;
import com.theironyard.entities.Favorite;
import com.theironyard.entities.User;
import com.theironyard.services.EventRepository;
import com.theironyard.services.FavoriteRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by zach on 3/14/16.
 */
@Controller
public class CalendarSpringController {
    @Autowired
    EventRepository events;

    @Autowired
    UserRepository users;

    @Autowired
    FavoriteRepository favorites;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        List<Event> eventEntities = events.findAllByOrderByDateTimeDesc();
        if (userName != null) {
            User user = users.findFirstByName(userName);

            model.addAttribute("user", user);
            model.addAttribute("now", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

            for (Event event : eventEntities) {
                event.setShowFavButton(favorites.findByUserAndEvent(user, event) == null);
            }
        }

        model.addAttribute("events", eventEntities);
        return "home";
    }

    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(HttpSession session, String description, String dateTime) {
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            Event event = new Event(description, LocalDateTime.parse(dateTime), users.findFirstByName(userName));
            events.save(event);
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String name) {
        User user = users.findFirstByName(name);
        if (user == null) {
            user = new User(name);
            users.save(user);
        }
        session.setAttribute("userName", name);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/fav", method = RequestMethod.POST)
    public String fav(HttpSession session, int id) {
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            User user = users.findFirstByName(userName);
            Event event = events.findOne(id);
            favorites.save(new Favorite(user, event));
        }
        return "redirect:/";
    }
}
