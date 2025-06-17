package com.luisangel.jwtapi.service;

import com.luisangel.jwtapi.models.*;
import com.luisangel.jwtapi.repository.ReaccionRepository;
import com.luisangel.jwtapi.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;

@Service
public class ReaccionService {

    @Autowired
    private ReaccionRepository reaccionRepository;

    @Autowired
    private TweetRepository tweetRepository;

    public Reaccion reaccionarATweet(EReaction tipo, Long tweetId, User user) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet no encontrado"));

        Optional<Reaccion> existente = reaccionRepository.findByUserAndTweet(user, tweet);

        if (existente.isPresent()) {
            Reaccion reaccion = existente.get();
            reaccion.setTipo(tipo); // Actualiza tipo si ya existe
            return reaccionRepository.save(reaccion);
        }

        Reaccion nueva = new Reaccion(tipo, tweet, user);
        return reaccionRepository.save(nueva);
    }

    public void quitarReaccion(Long tweetId, User user) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet no encontrado"));

        reaccionRepository.findByUserAndTweet(user, tweet)
                .ifPresent(reaccionRepository::delete);
    }

    public List<Reaccion> obtenerReaccionesDeTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet no encontrado"));

        return reaccionRepository.findByTweet(tweet);
    }

    public long contarReaccionesPorTipo(Long tweetId, EReaction tipo) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new RuntimeException("Tweet no encontrado"));

        return reaccionRepository.countByTweetAndTipo(tweet, tipo);
    }
public Map<EReaction, Long> obtenerConteoPorTipo(Long tweetId) {
    Tweet tweet = tweetRepository.findById(tweetId)
            .orElseThrow(() -> new RuntimeException("Tweet no encontrado"));
    List<Reaccion> reacciones = reaccionRepository.findByTweet(tweet);
    return reacciones.stream()
            .collect(Collectors.groupingBy(Reaccion::getTipo, Collectors.counting()));
}

}
