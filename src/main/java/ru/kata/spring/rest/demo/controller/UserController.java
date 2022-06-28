package ru.kata.spring.rest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

//    private final UserServisImp userServisImp;
//
//    @Autowired
//    public UserController(UserServisImp userServisImp) {
//        this.userServisImp = userServisImp;
//    }

    @GetMapping("/user")
    public String user(){
//            @AuthenticationPrincipal UserDetails userDetails, ModelMap model) {
//        model.addAttribute("user", userServisImp.getUserByName(userDetails.getUsername()));
        return "user";
    }

}
