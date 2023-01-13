package ru.kata.spring.boot_security.demo.util;

import lombok.AllArgsConstructor;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class Util {
    private final UserService service;


    @PostConstruct
    public void testStart() {



    }


    }


