package com.luisangel.jwtapi.controllers;

import com.luisangel.jwtapi.models.Reaction;
import com.luisangel.jwtapi.models.Tweet;
import com.luisangel.jwtapi.models.TweetReaction;
import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.payload.request.TweetReactionRequest;
import com.luisangel.jwtapi.repository.ReactionRepository;
import com.luisangel.jwtapi.repository.TweetRepository;
import com.luisangel.jwtapi.repository.UserRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reactions")
public class TweetReactionController {

    @Autowired
    private com.luisangel.jwtapi.repository.TweetReactionRepository tweetReactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ReactionRepository reactionRepository;

    @GetMapping("/all")
    public Page<TweetReaction> getAllReactions(Pageable pageable) {
        return tweetReactionRepository.findAll(pageable);
    }

    @PostMapping("/create")
    public TweetReaction createReaction(@Valid @RequestBody TweetReactionRequest request) {
        System.out.println("📨 Crear reacción -> tweetId: " + request.getTweetId() + ", reactionId: " + request.getReactionId());

        // Obtener usuario autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("❌ Usuario no encontrado: " + username));

        // Obtener tweet
        Tweet tweet = tweetRepository.findById(request.getTweetId())
                .orElseThrow(() -> new RuntimeException("❌ Tweet no encontrado"));

        // Obtener reacción
        Reaction reaction = reactionRepository.findById(request.getReactionId())
                .orElseThrow(() -> new RuntimeException("❌ Reacción no encontrada"));

        // Crear y guardar objeto
        TweetReaction tweetReaction = new TweetReaction();
        tweetReaction.setUser(user);
        tweetReaction.setTweet(tweet);
        tweetReaction.setReaction(reaction);

        tweetReactionRepository.save(tweetReaction);

        return tweetReaction;
    }
}
