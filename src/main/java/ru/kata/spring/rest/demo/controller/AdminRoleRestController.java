package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.service.RoleService;

import java.util.Set;

@RestController
//@RequestMapping("/api")
public class AdminRoleRestController {

    private final RoleService roleService;

    @Autowired
    public AdminRoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/rol")
    public Set<Role> AllRole(){
        Set<Role> allRole = roleService.findAll();
        return  allRole;
    }

}
