package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.service.RoleServiceImp;
import ru.kata.spring.rest.demo.service.UserServiceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminUserRestController {
    private final UserServiceImp userServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public AdminUserRestController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/admin/user/{id}")
    public User dableUser(@PathVariable(value="id") long id){
        return userServiceImp.getUserById(id);
    }

    @GetMapping("/admin/user")
    public List<User> allUser(){
        List<User> allUser = userServiceImp.all();
        return  allUser;
    }

    @DeleteMapping("/admin/user/delete/{id}")
    public void delete( @PathVariable(value="id") long id){
        userServiceImp.delete(id);
    }

    @PostMapping("/admin/user/create")
    public User createUser(@RequestBody User newUser, @RequestParam("roles") String [] roles){
        Set<Role> user = new HashSet<>();
        user.add(roleServiceImp.getRoleByRole(roles[0]));
        newUser.setRoles(user);
        userServiceImp.add(newUser);
        return newUser;
    }

    @PutMapping("/admin/user/update")
    public User updateUser(@RequestBody User updateUser, @RequestParam("roles") String [] roles
    ){
        Set <Role> user = new HashSet<>();
        user.add(roleServiceImp.getRoleByRole(roles[0]));
        updateUser.setRoles(user);
        userServiceImp.change(updateUser);
        return updateUser;
    }

}
