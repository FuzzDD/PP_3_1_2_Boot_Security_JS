package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDaoImpl userDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public List<User> getUsersList() {
        return userDao.getUsersList();
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    @Transactional
    public User show(Long id) {
        return userDao.show(id);
    }

    @Override
    @Transactional
    public void update(Long id, User updatetUser) {
        if (updatetUser.getRoles() == null) {
            updatetUser.setRoles(userDao.show(id).getRoles());
        } else {
            updatetUser.setRoles(updatetUser.getRoles());
        }
        if (updatetUser.getPassword().equals("")) {
            updatetUser.setPassword(userDao.show(id).getPassword());
        } else {
            updatetUser.setPassword(passwordEncoder.encode(updatetUser.getPassword()));
        }
        userDao.update(id, updatetUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public User getUserByLogin(String username) {
        return userDao.getUserByLogin(username);
    }

    @Override
    public User findByName(String email) {
        return userDao.findByName(email);
    }

}
