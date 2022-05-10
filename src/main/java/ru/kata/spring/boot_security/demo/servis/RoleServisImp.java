package ru.kata.spring.boot_security.demo.servis;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.Set;

@Service
@Transactional
public class RoleServisImp implements RoleServis{
    private RoleRepository roleRepository;

    public RoleServisImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        return  roleRepository.findAll();
    }

    @Override
    public Role getRoleByID(Long id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public Role getRoleByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
