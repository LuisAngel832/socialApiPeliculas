package com.luisangel.jwtapi.repository;

import com.luisangel.jwtapi.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {

    Optional<Reaccion> findByUserAndTweet(User user, Tweet tweet);

    List<Reaccion> findByTweet(Tweet tweet);

    long countByTweetAndTipo(Tweet tweet, EReaction tipo);
}
