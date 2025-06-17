package com.luisangel.jwtapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reacciones")
public class Reaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EReaction tipo;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Reaccion() {}

    public Reaccion(EReaction tipo, Tweet tweet, User user) {
        this.tipo = tipo;
        this.tweet = tweet;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public EReaction getTipo() {
        return tipo;
    }

    public void setTipo(EReaction tipo) {
        this.tipo = tipo;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
