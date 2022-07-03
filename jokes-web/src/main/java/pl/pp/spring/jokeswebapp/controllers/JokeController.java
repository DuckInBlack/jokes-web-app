package pl.pp.spring.jokeswebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokeController {

    @RequestMapping("/jokes/add")
        public String addJokeForm(){
            return "jokes/add";
        }
    }

