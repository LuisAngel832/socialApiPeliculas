package com.luisangel.jwtapi.repository;

import com.luisangel.jwtapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
