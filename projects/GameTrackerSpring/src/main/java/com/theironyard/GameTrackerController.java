package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by zach on 3/8/16.
 */
@Controller
public class GameTrackerController {
    @Autowired
    GameRepository games;

    @Autowired
    UserRepository users;

    @PostConstruct
    public void init() {
        System.out.println("Started up!");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String genre, Integer releaseYear, String platform) {
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        if (user != null) {
            model.addAttribute("user", user);
        }

        if (platform != null) {
            model.addAttribute("games", games.findByPlatformStartsWith(platform));
        }
        else if (genre != null && releaseYear != null) {
            model.addAttribute("games", games.findByUserAndGenreAndReleaseYearIsGreaterThanEqual(user, genre, releaseYear));
        }
        else if (genre != null) {
            model.addAttribute("games", games.findByUserAndGenre(user, genre));
        }
        else {
            model.addAttribute("games", games.findByUser(user));
        }
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) {
        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear);
        String userName = (String) session.getAttribute("userName");
        User user = users.findFirstByName(userName);
        game.user = user;
        games.save(game);
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName);
            users.save(user);
        }
        session.setAttribute("userName", userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
