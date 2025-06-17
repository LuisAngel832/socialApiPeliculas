package com.luisangel.jwtapi.controllers;

import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.repository.UserRepository;
import com.luisangel.jwtapi.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public Map<String, String> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
        return response;
    }

    @PostMapping("/signin")
    public Map<String, String> loginUser(@RequestBody Map<String, String> loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.get("username"),
                        loginRequest.get("password")
                )
        );
        String token = jwtUtils.generateJwtToken(authentication.getName(), "ROLE_USER");
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
