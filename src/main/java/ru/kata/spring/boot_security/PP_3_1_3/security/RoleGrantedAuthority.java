package ru.kata.spring.boot_security.PP_3_1_3.security;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.PP_3_1_3.model.Role;

public class RoleGrantedAuthority implements GrantedAuthority {

    private final Role role;
    public RoleGrantedAuthority(Role role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        System.out.println(role.getName());
        return role.getName();
    }
}
