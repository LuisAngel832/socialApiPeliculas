package com.luisangel.jwtapi.service;

import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User buscarPorUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
