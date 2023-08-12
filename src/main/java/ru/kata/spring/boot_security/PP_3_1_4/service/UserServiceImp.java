package ru.kata.spring.boot_security.PP_3_1_4.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.validation.annotation.Validated;
import ru.kata.spring.boot_security.PP_3_1_4.model.User;
import ru.kata.spring.boot_security.PP_3_1_4.repository.RoleRepository;
import ru.kata.spring.boot_security.PP_3_1_4.repository.UserRepository;


import java.util.List;

@Service
@Validated
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, RoleRepository roleRepository1, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository1;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> index() {

        return userRepository.findAll();
    }

    @Override
    public User show(int id) {
        return userRepository.findById((long)id).orElseThrow();
    }

    @Override
    public User showOne() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById((long) id);
    }

    @Transactional
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}