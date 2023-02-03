
package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userDao;
    @Autowired
    public UserController(UserService userDao) {
        this.userDao = userDao;
    }
    @GetMapping("user")
    public String pageForUser (Model model, Principal principal) {
        model.addAttribute("user",userDao.getUserByLogin(principal.getName()));
        return "user";
    }
    @GetMapping("admin")
    public String index(Model model) {
        model.addAttribute("users", userDao.getUsersList());
        return "admin";
    }
    @GetMapping("addUser")
    public String addUser(Model model) {
    model.addAttribute("user", new User());
    return "new";
    }
   @PostMapping("create")
    public  String create(@ModelAttribute("user") User user) {
    userDao.add(user);
    return "redirect:/admin";
    }
    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
    model.addAttribute("user", userDao.show(id));
    return "edit";
    }
    @PatchMapping("edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userDao.update(id, user);
        return "redirect:/admin";
    }
    @PostMapping("del")
    public String delete(@RequestParam("id") Long id) {
       userDao.delete(id);
        return "redirect:/admin";
    }
}

