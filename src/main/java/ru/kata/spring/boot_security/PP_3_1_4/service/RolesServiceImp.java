package ru.kata.spring.boot_security.PP_3_1_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.PP_3_1_4.model.Role;
import ru.kata.spring.boot_security.PP_3_1_4.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Component
public class RolesServiceImp implements RolesService{
    private final RoleRepository roleRepository;

    @Autowired
    public RolesServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getRoles() {
        List<Role> list = roleRepository.findAll();
        return new HashSet<>(list);
    }
}
