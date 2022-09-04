package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;
import pl.pp.spring.jokeswebapp.services.db.CategoryDbService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    private Logger log = LoggerFactory.getLogger(IndexController.class);

    private final JokeService jokeService;
    private final CategoryService categoryService;

    public IndexController(JokeService jokeService, CategoryService categoryService) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/", "/index"})
    public String showIndex(Model model) {
        log.info("showMainPage");

        model.addAttribute("jokes", jokeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }
}
