package ru.kata.spring.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.models.User;
import ru.kata.spring.rest.demo.servis.RoleServisImp;
import ru.kata.spring.rest.demo.servis.UserServisImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserServisImp userServisImp;
    private final RoleServisImp roleServisImp;

    @Autowired
    public RestUserController(UserServisImp userServisImp, RoleServisImp roleServisImp) {
        this.userServisImp = userServisImp;
        this.roleServisImp = roleServisImp;
    }

    @GetMapping("/users")
    public User tableUser(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/admin/{id}")
    public User dableUser(@PathVariable(value="id") long id){
        return userServisImp.getUserBiId(id);
    }

    @GetMapping("/admin")
    public List<User> allUser(){
//            ResponseEntity <List<User>> responseEntity =
        List<User> allUser = userServisImp.all();
        return  allUser;
    }

    @GetMapping("/rol")
    public Set<Role> AllRole(){
        Set<Role> allRole = roleServisImp.findAll();
        return  allRole;
    }

    @DeleteMapping("/delete/{id}")
    public void delete( @PathVariable(value="id") long id){
        userServisImp.delete(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User newUser, @RequestParam("roles") String [] roles){
        Set <Role> user = new HashSet<>();
        user.add(roleServisImp.getRoleByRole(roles[0]));
        newUser.setRoles(user);
        userServisImp.add(newUser);
        return newUser;
    }

        @PutMapping("/update")
    public User updateUser(@RequestBody User updateUser, @RequestParam("roles") String [] roles
////            @RequestParam("roles") Set<Role> roles, User user, @RequestParam(value = "id") Long id
//            @ModelAttribute("user1") User user1, @RequestParam("roles") Set<Role> roles
    ){
//            Role rolUser = new Role(roles[0]);
            Set <Role> user = new HashSet<>();
            user.add(roleServisImp.getRoleByRole(roles[0]));
            updateUser.setRoles(user);
////        userServisImp.add(user);
        userServisImp.change(updateUser);
        return updateUser;
    }

}


