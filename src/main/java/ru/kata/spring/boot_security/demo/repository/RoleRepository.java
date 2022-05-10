package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleById(Long id);

    Role findRoleByRole(String role);
    Set<Role> findAll();

}
