package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getRoleList() {
        return roleRepository.findAll().stream().collect(Collectors.toSet());
    }

//    @Override
//    public void addRole(Role role) {
//        roleRepository.save(role);
//    }
//    @Override
//    public Role getRoleById(Long id) {
//        Optional<Role> role = roleRepository.findById(id);
//        return role.orElse(null);
//    }
}
