package com.task.todoshare.controllers;

import com.task.todoshare.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    GreetingService greetingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Home() {

        return greetingService.greet();
    }
}
