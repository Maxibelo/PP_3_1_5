package ru.kata.spring.boot_security.PP_3_1_5.util;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException(String message) {
        super(message);
    }
}