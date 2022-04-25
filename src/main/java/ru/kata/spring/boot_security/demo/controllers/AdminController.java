package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("rolesList", userService.getAllRoles());
        model.addAttribute("newUser", new User());
        model.addAttribute("authorisedUser", (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return "admin";
    }
}