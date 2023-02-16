
package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class UserController {

    private final RoleService roleService;
    private final UserService userService;
    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }
    @GetMapping
    public String pageForUser (Model model, @AuthenticationPrincipal User userAuth) {
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("roles", roleService.getRoleList());
        model.addAttribute("user", new User());
        model.addAttribute("userAuth", userAuth);
        return "index";
    }
   @PostMapping("create")
    public  String create(@ModelAttribute("user") User user) {
    userService.add(user);
    return "redirect:/";
    }
    @PostMapping("edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(user.getId(), user);
        return "redirect:/";
    }
    @PostMapping("del")
    public String delete(@RequestParam("id") Long id) {
       userService.delete(id);
        return "redirect:/";
    }
}

