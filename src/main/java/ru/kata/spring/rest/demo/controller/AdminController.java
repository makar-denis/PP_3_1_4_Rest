package ru.kata.spring.rest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

//    private final UserServisImp userServisImp;
//    private final RoleServisImp roleServisImp;
//
//    @Autowired
//    public AdminController(UserServisImp userServisImp, RoleServisImp roleServisImp) {
//        this.userServisImp = userServisImp;
//        this.roleServisImp = roleServisImp;
//    }

    @GetMapping("/admin")
    public String allUser(){
//            @AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
//        model.addAttribute("user", userServisImp.getUserByName(userDetails.getUsername()));
//        model.addAttribute("listUsers", userServisImp.all());
//        model.addAttribute("newUser", new User());
//        model.addAttribute("rol", roleServisImp.findAll());
        return "admin";
    }

}
