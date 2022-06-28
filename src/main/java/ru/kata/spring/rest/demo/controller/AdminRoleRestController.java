package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.service.RoleServiceImp;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRoleRestController {

    private final RoleServiceImp roleServiceImp;

    @Autowired
    public AdminRoleRestController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/admin/rol")
    public Set<Role> AllRole(){
        Set<Role> allRole = roleServiceImp.findAll();
        return  allRole;
    }

}
