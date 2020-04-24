package com.task.todoshare.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloControllerRest {

    @RequestMapping("/")
    public String Home() {
        return "Hello from Docker!";
    }
}
