package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.service.RoleService;
import ru.kata.spring.rest.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Post {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public Post(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService=roleService;
    }

    @PostConstruct
    public void init() {
        Role rolUser = new Role("ROLE_USER");
        Role rolAdmin = new Role("ROLE_ADMIN");
        roleService.addRole(rolUser);
        roleService.addRole(rolAdmin);
        Set <Role> user = new HashSet<>();
        user.add(rolUser);
        Set <Role> admin = new HashSet<>();
        admin.add(rolAdmin);
        Set <Role> ua = new HashSet<>();
        ua.add(rolAdmin);
        ua.add(rolUser);

        userService.add(new User("1", "1", 1, "1", "1", user));
        userService.add(new User("2", "2", 2, "2", "2", admin));
        userService.add(new User("3", "3", 3, "3", "3", ua));
        userService.add(new User("4", "4", 4, "4", "4"));

    }
}
