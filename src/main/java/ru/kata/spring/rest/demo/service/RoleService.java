package ru.kata.spring.rest.demo.service;

import ru.kata.spring.rest.demo.models.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findAll();
    Role getRoleById(Long id);
    Role getRoleByRole(String role);
    void addRole(Role role);
}
