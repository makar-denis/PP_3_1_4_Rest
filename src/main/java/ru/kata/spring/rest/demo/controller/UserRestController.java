package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.service.RoleServiceImp;
import ru.kata.spring.rest.demo.service.UserServiceImp;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public UserRestController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/user")
    public User tableUser(@AuthenticationPrincipal User user){
        return user;
    }
}
