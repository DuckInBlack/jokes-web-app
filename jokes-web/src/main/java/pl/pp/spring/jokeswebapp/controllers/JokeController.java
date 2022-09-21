package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class JokeController {
    private Logger log = LoggerFactory.getLogger(JokeController.class);

    private final CategoryService categoryService;
    private final JokeService jokeService;

    public JokeController(CategoryService categoryService, JokeService jokeService) {
        this.categoryService = categoryService;
        this.jokeService = jokeService;
    }

    @GetMapping("/jokes/add")
    public String addJokeForm(Model model) {
        log.info("addJokeForm");

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("joke", new Joke());
        return "jokes/add";
    }

    @GetMapping("/jokes")
    public String showJokesForCategory(Model model, @RequestParam("categoryId") Long categoryId) {
        log.info("showJokesForCategory categoryId: {}", categoryId);

        Category category = categoryService.findById(categoryId);
        Set<Joke> jokes = category == null ? new HashSet<>() : category.getJokes();
        model.addAttribute("jokes", jokes);
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @PostMapping("/jokes/add")
    public String addJoke(@ModelAttribute Joke joke, @RequestParam("category") List<Long> categoryIds){
        log.info("addJoke joke: {}, categories: {}", joke, categoryIds);

        System.out.println(joke);

        Set<Category> categories = new HashSet<>();

        for(Long id: categoryIds){
            Category category = categoryService.findById(id);
            category.getJokes().add(joke);
            categoryService.save(category);
            categories.add(category);
        }

        System.out.println(categories);

        joke.setCategories(categories);

        jokeService.save(joke);
        return "redirect:/";
    }
}

