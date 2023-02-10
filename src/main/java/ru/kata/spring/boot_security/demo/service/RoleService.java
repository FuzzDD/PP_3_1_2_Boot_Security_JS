package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public Set<Role> getRoleList();
    //public void addRole (Role role);
    //public Role getRoleById(Long id);
}
