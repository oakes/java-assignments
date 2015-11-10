package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zach on 11/10/15.
 */
@Controller
public class BeerTrackerController {
    @Autowired
    BeerRepository beers;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("beers", beers.findAll());
        return "home";
    }

    @RequestMapping("/add-beer")
    public String addBeer(String beername, String beertype) {
        Beer beer = new Beer();
        beer.name = beername;
        beer.type = beertype;
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
}
