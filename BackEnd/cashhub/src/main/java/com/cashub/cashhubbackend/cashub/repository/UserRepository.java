package com.cashub.cashhubbackend.cashub.repository;

import com.cashub.cashhubbackend.cashub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRepository {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return save(user);
    }

    // Outros métodos para manipular usuários
    static User save(User user) {
        System.out.println("I just got executed!");
        return user;
    }
}

