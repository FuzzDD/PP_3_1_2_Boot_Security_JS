package ru.kata.spring.boot_security.demo.service;





import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsersList();
    void add(User user);
    public User show(Long id);
    public void update(Long id, User updatetUser);
    public void delete(Long id);
    public User getUserByLogin(String username);
    public User findByName(String email);
}
