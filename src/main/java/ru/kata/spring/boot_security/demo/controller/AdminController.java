package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servis.RoleServisImp;
import ru.kata.spring.boot_security.demo.servis.UserServisImp;

import java.util.Set;

@Controller
public class AdminController {

    private final UserServisImp userServisImp;
    private final RoleServisImp roleServisImp;

    @Autowired
    public AdminController(UserServisImp userServisImp, RoleServisImp roleServisImp) {
        this.userServisImp = userServisImp;
        this.roleServisImp = roleServisImp;
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
        model.addAttribute("user", userServisImp.getUserByName(userDetails.getUsername()));
        return "user";
    } // возвращаю конкретного user с ролью USER



    @GetMapping("/admin")
    public String allUser( @AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
        model.addAttribute("user", userServisImp.getUserByName(userDetails.getUsername()));

        model.addAttribute("listUsers", userServisImp.all());
        model.addAttribute("newUser", new User());
        model.addAttribute("rol", roleServisImp.findAll());


        return "admin";
    }

//    @GetMapping("/admin/create")
//    public String createUserForm(ModelMap model){
//        model.addAttribute("user", new User());
//        model.addAttribute("rol", roleServisImp.findAll());
//        return "create";
//    }

    @PostMapping("/admin/create")
    public String createUser(User newUser, @RequestParam("roles") Set<Role> roles){
        newUser.setRoles(roles);
        userServisImp.add(newUser);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete")
    public String deleteUser( @RequestParam(value="id") long id){
        userServisImp.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping("/admin/update/{id}")
//    public String updateUserForm(@PathVariable("id") Long id, ModelMap model){
//        User user = userServisImp.getUser(id);
//        model.addAttribute("user", user);
//        model.addAttribute("rol", roleServisImp.findAll());
//        return "update";
//    }

    @PutMapping("/admin/update")
    public String updateUser(
//            @RequestParam("roles") Set<Role> roles, User user, @RequestParam(value = "id") Long id
            @ModelAttribute("user1") User user1, @RequestParam("roles") Set<Role> roles
    ){
        user1.setRoles(roles);
//        userServisImp.add(user);
        userServisImp.change(user1);
        return "redirect:/admin";
    }


}
