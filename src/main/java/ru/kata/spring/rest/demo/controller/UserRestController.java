package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.service.RoleService;
import ru.kata.spring.rest.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public User tableUser(@AuthenticationPrincipal User user){
        return user;
    }
}
