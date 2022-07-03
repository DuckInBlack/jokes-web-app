package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.JokeService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JokeController {

    private final CategoryService categoryService;
    private final JokeService jokeService;

    public JokeController(CategoryService categoryService, JokeService jokeService) {
        this.categoryService = categoryService;
        this.jokeService = jokeService;
    }

    @GetMapping("/jokes/add")
    public String addJokeForm(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("joke", new Joke());
        return "jokes/add";
    }

    @RequestMapping("/jokes")
    public String showIndex(Model model, @RequestParam("categoryId") Long categoryId) {
        model.addAttribute("jokes", categoryService.findById(categoryId).getJokes());
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @PostMapping("/jokes/add")
    public String addJoke(@ModelAttribute Joke joke, @RequestParam("category") List<Long> categoryIds){
        System.out.println(joke);

        List<Category> categories = new ArrayList<>();

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

