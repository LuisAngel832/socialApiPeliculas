package com.luisangel.jwtapi.repository;

import com.luisangel.jwtapi.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
