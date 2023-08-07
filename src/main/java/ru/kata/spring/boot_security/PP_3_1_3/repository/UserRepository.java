package ru.kata.spring.boot_security.PP_3_1_3.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.PP_3_1_3.model.User;
import java.util.Optional;

    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        @Query("Select u from User u left join fetch u.roles where u.username=:username")
        Optional<User> findByUsername(@Param("username")String username);

}