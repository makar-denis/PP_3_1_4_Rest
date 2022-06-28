package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.service.RoleServiceImp;
import ru.kata.spring.rest.demo.service.UserServiceImp;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Post {

    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;
    @Autowired
    public Post(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp=roleServiceImp;
    }

    @PostConstruct
    public void init() {
        Role rolUser = new Role("ROLE_USER");
        Role rolAdmin = new Role("ROLE_ADMIN");
        roleServiceImp.addRole(rolUser);
        roleServiceImp.addRole(rolAdmin);
        Set <Role> user = new HashSet<>();
        user.add(rolUser);
        Set <Role> admin = new HashSet<>();
        admin.add(rolAdmin);
        Set <Role> ua = new HashSet<>();
        ua.add(rolAdmin);
        ua.add(rolUser);

        userServiceImp.add(new User("1", "1", 1, "1", "1", user));
        userServiceImp.add(new User("2", "2", 2, "2", "2", admin));
        userServiceImp.add(new User("3", "3", 3, "3", "3", ua));
        userServiceImp.add(new User("4", "4", 4, "4", "4"));

    }
}
