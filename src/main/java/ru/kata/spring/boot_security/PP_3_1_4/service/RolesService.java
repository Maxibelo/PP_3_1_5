package ru.kata.spring.boot_security.PP_3_1_4.service;

import ru.kata.spring.boot_security.PP_3_1_4.model.Role;

import java.util.Set;

public interface RolesService {
    Set<Role> getRoles();
}
