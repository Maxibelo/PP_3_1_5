package ru.kata.spring.boot_security.PP_3_1_5.util;

public class UserResponseException {
    private String info;

    public UserResponseException(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
