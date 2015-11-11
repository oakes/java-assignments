package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zach on 11/10/15.
 */
@Controller
public class BeerTrackerController {
    @Autowired
    BeerRepository beers;

    @Autowired
    UserRepository users;

    @PostConstruct
    public void init() {
        User user = users.findOneByName("Zach");
        if (user == null) {
            user = new User();
            user.name = "Zach";
            users.save(user);
        }
    }

    @RequestMapping("/")
    public String home(
            HttpServletRequest request,
            Model model,
            String type,
            Integer calories,
            String search,
            String showMine
    ) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "login";
        }

        if (showMine != null) {
            model.addAttribute("beers", users.findOneByName(username).beers);
        }
        else if (search != null) {
            model.addAttribute("beers", beers.searchByName(search));
        }
        else if (type != null && calories != null) {
            model.addAttribute("beers", beers.findByTypeAndCaloriesIsLessThanEqual(type, calories));
        }
        else if (type != null) {
            model.addAttribute("beers", beers.findByTypeOrderByNameAsc(type));
        }
        else {
            model.addAttribute("beers", beers.findAll());
        }
        return "home";
    }

    @RequestMapping("/add-beer")
    public String addBeer(String beername, String beertype, Integer beercalories, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = users.findOneByName(username);

        Beer beer = new Beer();
        beer.name = beername;
        beer.type = beertype;
        beer.calories = beercalories;
        beer.user = user;
        beers.save(beer);
        return "redirect:/";
    }

    @RequestMapping("/edit-beer")
    public String editBeer(Integer id, String name, String type) {
        Beer beer = beers.findOne(id);
        beer.name = name;
        beer.type = type;
        beers.save(beer);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(String username, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        User user = users.findOneByName(username);
        if (user == null) {
            user = new User();
            user.name = username;
            users.save(user);
        }

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
