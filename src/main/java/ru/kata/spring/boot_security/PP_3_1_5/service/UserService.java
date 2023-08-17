package ru.kata.spring.boot_security.PP_3_1_5.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.kata.spring.boot_security.PP_3_1_5.model.User;

import java.util.List;

public interface UserService {

    List<User> index();

    User show(int id);

    void delete(int id);

    void update(User user);

    void save(User user);

}