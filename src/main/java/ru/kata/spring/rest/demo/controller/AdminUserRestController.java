package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.service.RoleService;
import ru.kata.spring.rest.demo.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController

public class AdminUserRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminUserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/user/{id}")
    public User dableUser(@PathVariable(value="id") long id){
        return userService.getUserById(id);
    }

    @GetMapping("/admin/user")
    public List<User> allUser(){
        List<User> allUser = userService.all();
        return  allUser;
    }

    @DeleteMapping("/admin/user/delete/{id}")
    public void delete( @PathVariable(value="id") long id){
        userService.delete(id);
    }

    @PostMapping("/admin/user/create")
    public List<User> createUser(@RequestBody @Valid User newUser, @RequestParam(required = false, name="roles") String [] roles){
        Set<Role> user = new HashSet<>();
        user.add(roleService.getRoleByRole(roles[0]));
        newUser.setRoles(user);
        userService.add(newUser);
        return userService.all();
    }

    @PutMapping("/admin/user/update")
    public User updateUser(@RequestBody @Valid User updateUser, @RequestParam(required = false, name="roles") String [] roles
    ){
        Set <Role> rolesSet = new HashSet<>();
        rolesSet.add(roleService.getRoleByRole(roles[0]));
        updateUser.setRoles(rolesSet);
        userService.change(updateUser);
        return updateUser;
    }

}
