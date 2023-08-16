package ru.kata.spring.boot_security.PP_3_1_5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.PP_3_1_5.model.Role;
import ru.kata.spring.boot_security.PP_3_1_5.model.User;
import ru.kata.spring.boot_security.PP_3_1_5.repository.RoleRepository;
import ru.kata.spring.boot_security.PP_3_1_5.service.RolesService;
import ru.kata.spring.boot_security.PP_3_1_5.service.UserService;

import java.util.List;
import java.util.Set;

//url любой команды содержит "api"
@RestController
@RequestMapping("/api")
public class RestAdminController {
    private final RolesService rolesService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public RestAdminController(RolesService rolesService, UserService userService, RoleRepository roleRepository) {
        this.rolesService = rolesService;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")
    public List<User> getAll() {
        List<User> allUsers = userService.index();
        return new ResponseEntity<>(allUsers, HttpStatus.OK).getBody();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") Long id) {

        User user = userService.show(Math.toIntExact(id));
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(user).getBody();
    }


    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "User with id = " + id + " was deleted";
    }

    @GetMapping("/roles")
    public Set<Role> allRoles() {
        Set<Role> roleList = rolesService.getRoles();
        System.out.println(roleList);
        return roleList;
    }
}
