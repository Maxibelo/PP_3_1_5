package ru.kata.spring.boot_security.PP_3_1_3.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.validation.annotation.Validated;
import ru.kata.spring.boot_security.PP_3_1_3.model.User;
import ru.kata.spring.boot_security.PP_3_1_3.repository.RoleRepository;
import ru.kata.spring.boot_security.PP_3_1_3.repository.UserRepository;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;


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

    public User showOne(Principal principal) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(principal.getName());
       
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
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
