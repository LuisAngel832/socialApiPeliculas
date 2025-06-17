package com.luisangel.jwtapi.controllers;

import com.luisangel.jwtapi.models.Comment;
import com.luisangel.jwtapi.models.User;
import com.luisangel.jwtapi.service.CommentService;
import com.luisangel.jwtapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comentarios")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/crear/{tweetId}")
    public Comment crearComentario(@PathVariable Long tweetId,
                                   @RequestBody Map<String, String> body,
                                   HttpServletRequest request) {
        String contenido = body.get("contenido");
        String username = (String) request.getAttribute("username");
        User usuario = userService.buscarPorUsername(username);
        return commentService.crearComentario(contenido, tweetId, usuario);
    }

    @GetMapping("/tweet/{tweetId}")
    public List<Comment> obtenerComentariosPorTweet(@PathVariable Long tweetId) {
        return commentService.obtenerComentariosPorTweet(tweetId);
    }
}
