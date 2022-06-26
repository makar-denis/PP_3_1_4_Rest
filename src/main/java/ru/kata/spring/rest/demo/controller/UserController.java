package ru.kata.spring.rest.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

//@Controller
@RestController
public class UserController {

    @GetMapping("/r")
    public String start(Principal principal){
        return "Hello "+principal.getName();// смотрю под каким пользователем я зашел использовал для проб
    }
}
