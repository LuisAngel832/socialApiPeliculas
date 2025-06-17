package com.luisangel.jwtapi.controllers;

import com.luisangel.jwtapi.models.Tweet;
import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.service.TweetService;
import com.luisangel.jwtapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @PostMapping("/crear")
    public Tweet crearTweet(@RequestBody Tweet tweet, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        User user = userService.buscarPorUsername(username);
        return tweetService.crearTweet(tweet, user);
    }

    @GetMapping("/publicos")
    public List<Tweet> listarTweets() {
        return tweetService.listarTweets();
    }
}
