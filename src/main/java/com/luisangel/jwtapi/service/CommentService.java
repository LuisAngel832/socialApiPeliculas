package com.luisangel.jwtapi.service;

import com.luisangel.jwtapi.models.Comment;
import com.luisangel.jwtapi.models.Tweet;
import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.repository.CommentRepository;
import com.luisangel.jwtapi.repository.TweetRepository;
import com.luisangel.jwtapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TweetRepository tweetRepository;

    public Comment crearComentario(String contenido, Long tweetId, User user) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet no encontrado"));
        Comment comentario = new Comment();
        comentario.setContenido(contenido);
        comentario.setTweet(tweet);
        comentario.setUsuario(user);
        comentario.setFechaCreacion(LocalDateTime.now());
        return commentRepository.save(comentario);
    }

    public List<Comment> obtenerComentariosPorTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new RuntimeException("Tweet no encontrado"));
        return commentRepository.findByTweet(tweet);
    }
}
