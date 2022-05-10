package ru.kata.spring.boot_security.demo.servis;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

public interface RoleServis {
    Set<Role> findAll();
    Role getRoleByID(Long id);
    Role getRoleByRole(String role);
    void addRole(Role role);
}
