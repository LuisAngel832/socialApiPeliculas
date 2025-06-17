package com.luisangel.jwtapi.repository;

import com.luisangel.jwtapi.models.Comment;
import com.luisangel.jwtapi.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTweet(Tweet tweet);
}
