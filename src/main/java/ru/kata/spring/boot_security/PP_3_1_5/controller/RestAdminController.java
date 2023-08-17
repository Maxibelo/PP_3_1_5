package ru.kata.spring.boot_security.PP_3_1_5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.PP_3_1_5.model.Role;
import ru.kata.spring.boot_security.PP_3_1_5.model.User;
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

    @Autowired
    public RestAdminController(RolesService rolesService, UserService userService) {
        this.rolesService = rolesService;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAll() {
        List<User> allUsers = userService.index();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
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
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public Set<Role> allRoles() {
        Set<Role> roleList = rolesService.getRoles();
        return ResponseEntity.ok(roleList).getBody();
    }
}
