package ru.kata.spring.boot_security.PP_3_1_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.PP_3_1_5.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
