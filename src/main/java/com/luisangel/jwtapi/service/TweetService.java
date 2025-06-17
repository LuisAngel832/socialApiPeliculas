package com.luisangel.jwtapi.service;

import com.luisangel.jwtapi.models.Tweet;
import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    public Tweet crearTweet(Tweet tweet, User user) {
        tweet.setPostedBy(user);
        tweet.setFechaCreacion(LocalDateTime.now());
        return tweetRepository.save(tweet);
    }

    public List<Tweet> listarTweets() {
        return tweetRepository.findAll();
    }
}
