package ru.kata.spring.rest.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.rest.demo.models.Role;
import ru.kata.spring.rest.demo.repository.RoleRepository;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImp implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Role> findAll() {
        return  roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findRoleById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
