package pl.pp.spring.jokeswebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pp.spring.jokeswebapp.services.UserService;

@Controller
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/users"})
    public String showIndex(Model model) {
        log.info("showListUsers");

        model.addAttribute("users", userService.findAll());
        return "users/list";
    }
}
