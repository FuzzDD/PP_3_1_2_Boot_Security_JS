package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/admin/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping//("/users")
    public List<User> getUserList() {
        List<User> userList = userService.getUsersList();
        return userList;
    }

    @GetMapping("/{id}")
    public User userInf(@PathVariable Long id) {
        return userService.show(id);
    }

    @PostMapping//("/users")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userService.add(user);
        return ResponseEntity.ok(new UserDto(user));
    }

    @PutMapping//("/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userService.update(userDto.getId(), user);
        return ResponseEntity.ok(new UserDto(user));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody UserDto userDto) {
        userService.delete(userDto.getId());
        return ok(HttpStatus.OK);
    }

}
