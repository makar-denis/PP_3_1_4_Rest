package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servis.RoleServisImp;
import ru.kata.spring.boot_security.demo.servis.UserServisImp;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Post {

    private final UserServisImp userServisImp;
    private final RoleServisImp roleServisImp;
    @Autowired
    public Post(UserServisImp userServisImp, RoleServisImp roleServisImp) {
        this.userServisImp = userServisImp;
        this.roleServisImp=roleServisImp;
    }

    @PostConstruct
    public void init() {
        Role rolUser = new Role("ROLE_USER");
        Role rolAdmin = new Role("ROLE_ADMIN");
        roleServisImp.addRole(rolUser);
        roleServisImp.addRole(rolAdmin);
        Set <Role> user = new HashSet<>();
        user.add(rolUser);
        Set <Role> admin = new HashSet<>();
        admin.add(rolAdmin);
        Set <Role> ua = new HashSet<>();
        ua.add(rolAdmin);
        ua.add(rolUser);

        userServisImp.add(new User("1", "1", "1@", user));
        userServisImp.add(new User("2", "2", "2@", admin));
        userServisImp.add(new User("3", "3", "3@", ua));

    }
}
