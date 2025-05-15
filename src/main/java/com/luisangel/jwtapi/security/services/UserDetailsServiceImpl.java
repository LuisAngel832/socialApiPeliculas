package com.luisangel.jwtapi.security.services;

import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Buscando usuario: " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("❌ Usuario no encontrado: " + username);
                    return new UsernameNotFoundException("Usuario no encontrado");
                });

        System.out.println("✅ Usuario encontrado: " + user.getUsername());
        return UserDetailsImpl.build(user);
    }

}
