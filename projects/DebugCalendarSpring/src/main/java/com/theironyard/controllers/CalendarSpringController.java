package com.theironyard.controllers;

import com.theironyard.entities.Event;
import com.theironyard.entities.User;
import com.theironyard.services.EventRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.util.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

/**
 * Created by zach on 11/15/15.
 */
@Controller
public class CalendarSpringController {

    @Autowired
    UserRepository users;

    @Autowired
    EventRepository events;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (users.count() == 0) {
            User user = new User("admin", PasswordHash.createHash("admin"));
            users.save(user);
        }

        if (events.count() == 0) {
            User user = users.findOneByUsername("admin");
            events.save(new Event(user, LocalDateTime.now(), "My 1st event"));
            events.save(new Event(user, LocalDateTime.now(), "My 2nd event"));
            events.save(new Event(user, LocalDateTime.now(), "My 3rd event"));
            events.save(new Event(user, LocalDateTime.now(), "My 4th event"));
            events.save(new Event(user, LocalDateTime.now(), "My 5th event"));
        }
    }

    @RequestMapping("/")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "login";
        }

        model.addAttribute("user", users.findOneByUsername(username));
        model.addAttribute("now", LocalDateTime.now());

        return "home";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = users.findOneByUsername(username);
        if (user == null) {
            user = new User();
            user.username = username;
            user.password = PasswordHash.createHash(password);
            users.save(user);
        }
        else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Wrong password.");
        }

        session.setAttribute("username", username);

        return "redirect:/";
    }

    @RequestMapping("/addevent")
    public String addEvent(HttpSession session, String description, String date) throws Exception {
        String username = (String) session.getAttribute("user");
        if (username == null) {
            throw new Exception("Not logged in.");
        }

        Event e = new Event();
        e.date = LocalDateTime.parse(date);
        e.user = users.findOneByUsername(username);
        events.save(e);

        return "redirect:/";
    }
}
