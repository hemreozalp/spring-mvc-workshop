package com.hemreozalp.spring_mvc.controller;

import com.hemreozalp.spring_mvc.model.User;
import com.hemreozalp.spring_mvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        boolean success = userService.registerUser(user);
        if (success) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Username or email already exists");
            return "register";
        }
    }
}
